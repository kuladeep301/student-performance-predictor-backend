package com.student.dto;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardSummaryDTO {
    private long totalStudents;
    private long totalPredictions;
    private double averagePredictedScore;
    private long lowRiskCount;
    private long mediumRiskCount;
    private long highRiskCount;
    private List<PredictionResponse> recentPredictions;
}
