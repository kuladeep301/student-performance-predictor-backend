package com.student.service;

import com.student.dto.StudentDTO;
import com.student.entity.Student;
import com.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public StudentDTO getStudentById(Long id) {
        Student s = studentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Student not found with ID: " + id));
        return mapToDTO(s);
    }

    @Transactional
    public StudentDTO createStudent(StudentDTO dto) {
        if (studentRepository.existsByStudentCode(dto.getStudentCode())) {
            throw new IllegalArgumentException("Student code already exists: " + dto.getStudentCode());
        }

        Student student = Student.builder()
                .studentCode(dto.getStudentCode())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .gender(dto.getGender())
                .age(dto.getAge())
                .department(dto.getDepartment())
                .build();

        Student saved = studentRepository.save(student);
        return mapToDTO(saved);
    }

    @Transactional
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new NoSuchElementException("Student not found with ID: " + id);
        }
        studentRepository.deleteById(id);
    }

    private StudentDTO mapToDTO(Student s) {
        return StudentDTO.builder()
                .id(s.getId())
                .studentCode(s.getStudentCode())
                .firstName(s.getFirstName())
                .lastName(s.getLastName())
                .email(s.getEmail())
                .gender(s.getGender())
                .age(s.getAge())
                .department(s.getDepartment())
                .createdAt(s.getCreatedAt())
                .build();
    }
}
