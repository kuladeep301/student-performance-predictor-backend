package com.student.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class PredictionResponse {

    private Long id;
    private Long studentId;
    private String studentName;
    private Double studyHoursPerWeek;
    private Double attendanceRate;
    private Integer pastFailures;
    private Double assignmentScore;
    private Double midtermExam;

    private Double predictedScore;
    private String predictedGrade; // 'A', 'B', 'C', 'D', 'F'
    private String riskLevel;      // 'LOW', 'MEDIUM', 'HIGH'
    private Double confidenceScore;

    private String recommendations;
    private List<String> actionItems;
    private Map<String, Object> featureContributions;
    private LocalDateTime createdAt;

    public PredictionResponse() {}

    public PredictionResponse(Long id, Long studentId, String studentName, Double studyHoursPerWeek, Double attendanceRate, Integer pastFailures, Double assignmentScore, Double midtermExam, Double predictedScore, String predictedGrade, String riskLevel, Double confidenceScore, String recommendations, List<String> actionItems, Map<String, Object> featureContributions, LocalDateTime createdAt) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.studyHoursPerWeek = studyHoursPerWeek;
        this.attendanceRate = attendanceRate;
        this.pastFailures = pastFailures;
        this.assignmentScore = assignmentScore;
        this.midtermExam = midtermExam;
        this.predictedScore = predictedScore;
        this.predictedGrade = predictedGrade;
        this.riskLevel = riskLevel;
        this.confidenceScore = confidenceScore;
        this.recommendations = recommendations;
        this.actionItems = actionItems;
        this.featureContributions = featureContributions;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public Double getStudyHoursPerWeek() { return studyHoursPerWeek; }
    public void setStudyHoursPerWeek(Double studyHoursPerWeek) { this.studyHoursPerWeek = studyHoursPerWeek; }

    public Double getAttendanceRate() { return attendanceRate; }
    public void setAttendanceRate(Double attendanceRate) { this.attendanceRate = attendanceRate; }

    public Integer getPastFailures() { return pastFailures; }
    public void setPastFailures(Integer pastFailures) { this.pastFailures = pastFailures; }

    public Double getAssignmentScore() { return assignmentScore; }
    public void setAssignmentScore(Double assignmentScore) { this.assignmentScore = assignmentScore; }

    public Double getMidtermExam() { return midtermExam; }
    public void setMidtermExam(Double midtermExam) { this.midtermExam = midtermExam; }

    public Double getPredictedScore() { return predictedScore; }
    public void setPredictedScore(Double predictedScore) { this.predictedScore = predictedScore; }

    public String getPredictedGrade() { return predictedGrade; }
    public void setPredictedGrade(String predictedGrade) { this.predictedGrade = predictedGrade; }

    public String getRiskLevel() { return riskLevel; }
    public void setRiskLevel(String riskLevel) { this.riskLevel = riskLevel; }

    public Double getConfidenceScore() { return confidenceScore; }
    public void setConfidenceScore(Double confidenceScore) { this.confidenceScore = confidenceScore; }

    public String getRecommendations() { return recommendations; }
    public void setRecommendations(String recommendations) { this.recommendations = recommendations; }

    public List<String> getActionItems() { return actionItems; }
    public void setActionItems(List<String> actionItems) { this.actionItems = actionItems; }

    public Map<String, Object> getFeatureContributions() { return featureContributions; }
    public void setFeatureContributions(Map<String, Object> featureContributions) { this.featureContributions = featureContributions; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private Long studentId;
        private String studentName;
        private Double studyHoursPerWeek;
        private Double attendanceRate;
        private Integer pastFailures;
        private Double assignmentScore;
        private Double midtermExam;
        private Double predictedScore;
        private String predictedGrade;
        private String riskLevel;
        private Double confidenceScore;
        private String recommendations;
        private List<String> actionItems;
        private Map<String, Object> featureContributions;
        private LocalDateTime createdAt;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder studentId(Long studentId) { this.studentId = studentId; return this; }
        public Builder studentName(String studentName) { this.studentName = studentName; return this; }
        public Builder studyHoursPerWeek(Double studyHoursPerWeek) { this.studyHoursPerWeek = studyHoursPerWeek; return this; }
        public Builder attendanceRate(Double attendanceRate) { this.attendanceRate = attendanceRate; return this; }
        public Builder pastFailures(Integer pastFailures) { this.pastFailures = pastFailures; return this; }
        public Builder assignmentScore(Double assignmentScore) { this.assignmentScore = assignmentScore; return this; }
        public Builder midtermExam(Double midtermExam) { this.midtermExam = midtermExam; return this; }
        public Builder predictedScore(Double predictedScore) { this.predictedScore = predictedScore; return this; }
        public Builder predictedGrade(String predictedGrade) { this.predictedGrade = predictedGrade; return this; }
        public Builder riskLevel(String riskLevel) { this.riskLevel = riskLevel; return this; }
        public Builder confidenceScore(Double confidenceScore) { this.confidenceScore = confidenceScore; return this; }
        public Builder recommendations(String recommendations) { this.recommendations = recommendations; return this; }
        public Builder actionItems(List<String> actionItems) { this.actionItems = actionItems; return this; }
        public Builder featureContributions(Map<String, Object> featureContributions) { this.featureContributions = featureContributions; return this; }
        public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }

        public PredictionResponse build() {
            return new PredictionResponse(id, studentId, studentName, studyHoursPerWeek, attendanceRate, pastFailures, assignmentScore, midtermExam, predictedScore, predictedGrade, riskLevel, confidenceScore, recommendations, actionItems, featureContributions, createdAt);
        }
    }
}
