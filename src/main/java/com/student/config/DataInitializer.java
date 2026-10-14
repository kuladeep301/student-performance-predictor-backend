package com.student.config;

import com.student.dto.PredictionRequest;
import com.student.entity.Student;
import com.student.repository.StudentRepository;
import com.student.service.PredictionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final StudentRepository studentRepository;
    private final PredictionService predictionService;

    @Override
    public void run(String... args) {
        if (studentRepository.count() == 0) {
            log.info("Seeding initial student dataset into MySQL database...");

            List<Student> sampleStudents = Arrays.asList(
                Student.builder().studentCode("STU-1001").firstName("Alex").lastName("Johnson").email("alex.j@university.edu").gender("Male").age(20).department("Computer Science").build(),
                Student.builder().studentCode("STU-1002").firstName("Sophia").lastName("Chen").email("sophia.c@university.edu").gender("Female").age(21).department("Data Science").build(),
                Student.builder().studentCode("STU-1003").firstName("Marcus").lastName("Taylor").email("marcus.t@university.edu").gender("Male").age(19).department("Information Technology").build(),
                Student.builder().studentCode("STU-1004").firstName("Elena").lastName("Rodriguez").email("elena.r@university.edu").gender("Female").age(22).department("Software Engineering").build(),
                Student.builder().studentCode("STU-1005").firstName("David").lastName("Kim").email("david.k@university.edu").gender("Male").age(20).department("Computer Science").build()
            );

            List<Student> saved = studentRepository.saveAll(sampleStudents);

            // Generate initial predictions for these students
            predictionService.predictAndSave(PredictionRequest.builder()
                .studentId(saved.get(0).getId())
                .studentName("Alex Johnson")
                .studyHoursPerWeek(18.0)
                .attendanceRate(92.0)
                .pastFailures(0)
                .assignmentScore(88.0)
                .midtermExam(85.0)
                .build());

            predictionService.predictAndSave(PredictionRequest.builder()
                .studentId(saved.get(1).getId())
                .studentName("Sophia Chen")
                .studyHoursPerWeek(22.0)
                .attendanceRate(97.0)
                .pastFailures(0)
                .assignmentScore(95.0)
                .midtermExam(92.0)
                .build());

            predictionService.predictAndSave(PredictionRequest.builder()
                .studentId(saved.get(2).getId())
                .studentName("Marcus Taylor")
                .studyHoursPerWeek(6.0)
                .attendanceRate(64.0)
                .pastFailures(2)
                .assignmentScore(52.0)
                .midtermExam(48.0)
                .build());

            predictionService.predictAndSave(PredictionRequest.builder()
                .studentId(saved.get(3).getId())
                .studentName("Elena Rodriguez")
                .studyHoursPerWeek(14.0)
                .attendanceRate(84.0)
                .pastFailures(0)
                .assignmentScore(78.0)
                .midtermExam(74.0)
                .build());

            log.info("Database seeding completed with {} students and initial predictions.", saved.size());
        }
    }
}
