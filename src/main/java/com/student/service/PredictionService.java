package com.student.service;

import com.student.dto.DashboardSummaryDTO;
import com.student.dto.PredictionRequest;
import com.student.dto.PredictionResponse;
import com.student.entity.Prediction;
import com.student.entity.Student;
import com.student.repository.PredictionRepository;
import com.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PredictionService {

    private final PredictionRepository predictionRepository;
    private final StudentRepository studentRepository;

    @Transactional
    public PredictionResponse predictAndSave(PredictionRequest request) {
        Student student = null;
        if (request.getStudentId() != null) {
            student = studentRepository.findById(request.getStudentId()).orElse(null);
        }

        // Multi-factor weighted regression formula
        double midterm = request.getMidtermExam();
        double assignments = request.getAssignmentScore();
        double attendance = request.getAttendanceRate();
        double studyHours = request.getStudyHoursPerWeek();
        int failures = request.getPastFailures();

        // 1. Core weighted calculation
        double studyHoursScore = Math.min((studyHours / 20.0) * 100.0, 100.0);
        double rawScore = (midterm * 0.35)
                + (assignments * 0.25)
                + (attendance * 0.20)
                + (studyHoursScore * 0.15)
                - (failures * 4.5);

        // Low attendance compounding penalty
        if (attendance < 75.0) {
            rawScore -= (75.0 - attendance) * 0.25;
        }

        // Bound final score between 0 and 100
        double finalScore = Math.max(0.0, Math.min(100.0, round(rawScore, 1)));

        // 2. Grade assignment
        String grade;
        if (finalScore >= 85.0) grade = "A";
        else if (finalScore >= 70.0) grade = "B";
        else if (finalScore >= 55.0) grade = "C";
        else if (finalScore >= 45.0) grade = "D";
        else grade = "F";

        // 3. Risk assessment
        String riskLevel;
        if (finalScore < 50.0 || failures >= 2 || attendance < 65.0) {
            riskLevel = "HIGH";
        } else if (finalScore < 70.0 || attendance < 80.0 || failures == 1) {
            riskLevel = "MEDIUM";
        } else {
            riskLevel = "LOW";
        }

        // 4. Model confidence
        double confidence = round(88.0 + (Math.abs(50.0 - Math.abs(50.0 - finalScore)) * 0.15), 1);
        confidence = Math.min(97.5, Math.max(82.0, confidence));

        // 5. Intelligent tailored recommendations
        List<String> actionItems = generateRecommendations(studyHours, attendance, failures, assignments, midterm);
        String summaryRec = String.join(" | ", actionItems);

        // Feature contributions breakdown
        Map<String, Object> contributions = new LinkedHashMap<>();
        contributions.put("midtermWeight", 35.0);
        contributions.put("assignmentWeight", 25.0);
        contributions.put("attendanceWeight", 20.0);
        contributions.put("studyHabitsWeight", 15.0);
        contributions.put("failurePenaltyDeductions", round(failures * 4.5, 1));
        contributions.put("normalizedStudyHoursScore", round(studyHoursScore, 1));

        // Save entity to database
        Prediction prediction = Prediction.builder()
                .student(student)
                .studyHoursPerWeek(studyHours)
                .attendanceRate(attendance)
                .pastFailures(failures)
                .assignmentScore(assignments)
                .midtermExam(midterm)
                .predictedScore(finalScore)
                .predictedGrade(grade)
                .riskLevel(riskLevel)
                .confidenceScore(confidence)
                .recommendations(summaryRec)
                .build();

        Prediction saved = predictionRepository.save(prediction);

        return mapToResponse(saved, actionItems, contributions, request.getStudentName());
    }

    public List<PredictionResponse> getAllPredictions() {
        return predictionRepository.findTop10ByOrderByCreatedAtDesc().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public PredictionResponse getPredictionById(Long id) {
        Prediction p = predictionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Prediction not found with ID: " + id));
        return mapToResponse(p);
    }

    public DashboardSummaryDTO getDashboardSummary() {
        long totalStudents = studentRepository.count();
        long totalPredictions = predictionRepository.count();
        Double avg = predictionRepository.findAveragePredictedScore();
        double avgScore = avg != null ? round(avg, 1) : 0.0;

        long highRisk = predictionRepository.countByRiskLevel("HIGH");
        long medRisk = predictionRepository.countByRiskLevel("MEDIUM");
        long lowRisk = predictionRepository.countByRiskLevel("LOW");

        List<PredictionResponse> recent = predictionRepository.findTop10ByOrderByCreatedAtDesc().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        return DashboardSummaryDTO.builder()
                .totalStudents(totalStudents)
                .totalPredictions(totalPredictions)
                .averagePredictedScore(avgScore)
                .highRiskCount(highRisk)
                .mediumRiskCount(medRisk)
                .lowRiskCount(lowRisk)
                .recentPredictions(recent)
                .build();
    }

    private List<String> generateRecommendations(double studyHours, double attendance, int failures, double assignments, double midterm) {
        List<String> items = new ArrayList<>();

        if (midterm < 60.0) {
            items.add("Attend midterm review sessions and focus on key conceptual gaps");
        }
        if (studyHours < 12.0) {
            items.add(String.format("Increase independent study from %.1f hrs/wk to at least 14-16 hrs/wk", studyHours));
        }
        if (attendance < 80.0) {
            items.add(String.format("Improve lecture attendance (currently %.1f%%) to a minimum of 85%% to prevent grade penalties", attendance));
        }
        if (assignments < 70.0) {
            items.add("Utilize lab hours and tutoring support for weekly assignment completion");
        }
        if (failures > 0) {
            items.add("Enroll in prerequisite refresher workshops to strengthen foundational coursework");
        }
        if (items.isEmpty()) {
            items.add("Outstanding progress! Maintain current study habits and consider peer mentorship");
            items.add("Prepare advanced study topics for honors or distinction opportunities");
        }
        return items;
    }

    private PredictionResponse mapToResponse(Prediction p) {
        List<String> items = p.getRecommendations() != null 
                ? Arrays.asList(p.getRecommendations().split(" \\| ")) 
                : Collections.emptyList();
        return mapToResponse(p, items, Collections.emptyMap(), null);
    }

    private PredictionResponse mapToResponse(Prediction p, List<String> actionItems, Map<String, Object> contributions, String fallbackName) {
        String studentName = fallbackName;
        Long studentId = null;
        if (p.getStudent() != null) {
            studentId = p.getStudent().getId();
            studentName = p.getStudent().getFirstName() + " " + p.getStudent().getLastName();
        }

        return PredictionResponse.builder()
                .id(p.getId())
                .studentId(studentId)
                .studentName(studentName != null ? studentName : "Student #" + (p.getId() != null ? p.getId() : "New"))
                .studyHoursPerWeek(p.getStudyHoursPerWeek())
                .attendanceRate(p.getAttendanceRate())
                .pastFailures(p.getPastFailures())
                .assignmentScore(p.getAssignmentScore())
                .midtermExam(p.getMidtermExam())
                .predictedScore(p.getPredictedScore())
                .predictedGrade(p.getPredictedGrade())
                .riskLevel(p.getRiskLevel())
                .confidenceScore(p.getConfidenceScore())
                .recommendations(p.getRecommendations())
                .actionItems(actionItems)
                .featureContributions(contributions)
                .createdAt(p.getCreatedAt())
                .build();
    }

    private double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
