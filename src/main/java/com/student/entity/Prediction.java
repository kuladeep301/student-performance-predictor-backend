package com.student.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "predictions")
public class Prediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id", nullable = true)
    private Student student;

    @Column(name = "study_hours_per_week")
    private Double studyHoursPerWeek;

    @Column(name = "attendance_rate")
    private Double attendanceRate;

    @Column(name = "past_failures")
    private Integer pastFailures;

    @Column(name = "assignment_score")
    private Double assignmentScore;

    @Column(name = "midterm_exam")
    private Double midtermExam;

    @Column(name = "predicted_score")
    private Double predictedScore;

    @Column(name = "predicted_grade", length = 5)
    private String predictedGrade;

    @Column(name = "risk_level", length = 20)
    private String riskLevel; // 'LOW', 'MEDIUM', 'HIGH'

    @Column(name = "confidence_score")
    private Double confidenceScore;

    @Column(columnDefinition = "TEXT")
    private String recommendations;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public Prediction() {}

    public Prediction(Long id, Student student, Double studyHoursPerWeek, Double attendanceRate, Integer pastFailures, Double assignmentScore, Double midtermExam, Double predictedScore, String predictedGrade, String riskLevel, Double confidenceScore, String recommendations, LocalDateTime createdAt) {
        this.id = id;
        this.student = student;
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
        this.createdAt = createdAt;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

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

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    // Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private Student student;
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
        private LocalDateTime createdAt;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder student(Student student) { this.student = student; return this; }
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
        public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }

        public Prediction build() {
            return new Prediction(id, student, studyHoursPerWeek, attendanceRate, pastFailures, assignmentScore, midtermExam, predictedScore, predictedGrade, riskLevel, confidenceScore, recommendations, createdAt);
        }
    }
}
