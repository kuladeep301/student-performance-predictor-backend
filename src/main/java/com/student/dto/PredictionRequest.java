package com.student.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class PredictionRequest {

    private Long studentId;
    private String studentName;

    @NotNull(message = "Study hours per week is required")
    @Min(value = 0, message = "Study hours cannot be negative")
    @Max(value = 80, message = "Study hours cannot exceed 80 hours/week")
    private Double studyHoursPerWeek;

    @NotNull(message = "Attendance rate is required")
    @Min(value = 0, message = "Attendance rate must be between 0 and 100")
    @Max(value = 100, message = "Attendance rate must be between 0 and 100")
    private Double attendanceRate;

    @NotNull(message = "Past failures count is required")
    @Min(value = 0, message = "Past failures cannot be negative")
    @Max(value = 15, message = "Past failures cannot exceed 15")
    private Integer pastFailures;

    @NotNull(message = "Assignment score is required")
    @Min(value = 0, message = "Assignment score must be between 0 and 100")
    @Max(value = 100, message = "Assignment score must be between 0 and 100")
    private Double assignmentScore;

    @NotNull(message = "Midterm exam score is required")
    @Min(value = 0, message = "Midterm exam score must be between 0 and 100")
    @Max(value = 100, message = "Midterm exam score must be between 0 and 100")
    private Double midtermExam;

    public PredictionRequest() {}

    public PredictionRequest(Long studentId, String studentName, Double studyHoursPerWeek, Double attendanceRate, Integer pastFailures, Double assignmentScore, Double midtermExam) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studyHoursPerWeek = studyHoursPerWeek;
        this.attendanceRate = attendanceRate;
        this.pastFailures = pastFailures;
        this.assignmentScore = assignmentScore;
        this.midtermExam = midtermExam;
    }

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

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long studentId;
        private String studentName;
        private Double studyHoursPerWeek;
        private Double attendanceRate;
        private Integer pastFailures;
        private Double assignmentScore;
        private Double midtermExam;

        public Builder studentId(Long studentId) { this.studentId = studentId; return this; }
        public Builder studentName(String studentName) { this.studentName = studentName; return this; }
        public Builder studyHoursPerWeek(Double studyHoursPerWeek) { this.studyHoursPerWeek = studyHoursPerWeek; return this; }
        public Builder attendanceRate(Double attendanceRate) { this.attendanceRate = attendanceRate; return this; }
        public Builder pastFailures(Integer pastFailures) { this.pastFailures = pastFailures; return this; }
        public Builder assignmentScore(Double assignmentScore) { this.assignmentScore = assignmentScore; return this; }
        public Builder midtermExam(Double midtermExam) { this.midtermExam = midtermExam; return this; }

        public PredictionRequest build() {
            return new PredictionRequest(studentId, studentName, studyHoursPerWeek, attendanceRate, pastFailures, assignmentScore, midtermExam);
        }
    }
}
