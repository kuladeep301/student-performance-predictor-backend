package com.student.repository;

import com.student.entity.Prediction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PredictionRepository extends JpaRepository<Prediction, Long> {
    List<Prediction> findByStudentIdOrderByCreatedAtDesc(Long studentId);
    List<Prediction> findTop10ByOrderByCreatedAtDesc();

    @Query("SELECT AVG(p.predictedScore) FROM Prediction p")
    Double findAveragePredictedScore();

    long countByRiskLevel(String riskLevel);
}
