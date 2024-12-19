package com.example.StudentGradeMS.Service.Service;

import com.example.StudentGradeMS.Model.Grade;
import com.example.StudentGradeMS.Repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    // Get all grades
    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }

    // Get grade by ID
    public Grade getGradeById(long id) {
        return gradeRepository.findById(id).orElse(null);
    }

    // Create a new grade
    public Grade createGrade(Grade grade) {
        return gradeRepository.save(grade);
    }

    // Update an existing grade
    public Grade updateGrade(long id, Grade newGrade) {
        return gradeRepository.findById(id)
                .map(existingGrade -> {
                    existingGrade.setGrade(newGrade.getGrade());
                    existingGrade.setStatus(newGrade.getStatus());
                    existingGrade.setGpa(newGrade.getGpa());
                    return gradeRepository.save(existingGrade);
                }).orElseThrow(() -> new RuntimeException("Grade not found with id " + id));
    }

    // Delete a grade
    public void deleteGrade(Long id) {
        gradeRepository.deleteById(id);
    }

    public void calculateGradeDetails(Grade grade) {
        int marks = grade.getMarks();

        // Determine grade status
        if (marks >= 50) {
            grade.setStatus("Pass");
        } else {
            grade.setStatus("Fail");
        }

        // Calculate GPA (example logic)
        if (marks >= 80) {
            grade.setGpa(4.0f);
            grade.setGrade('A');
        } else if (marks >= 65) {
            grade.setGpa(3.66f);
            grade.setGrade('B');
        } else if (marks >= 50) {
            grade.setGpa(3.33f);
            grade.setGrade('C');
        } else if (marks >= 35) {
            grade.setGpa(2.8f);
            grade.setGrade('D');
        } else if (marks >= 15) {
            grade.setGpa(2.5f);
            grade.setGrade('E');
        } else {
            grade.setGpa(1.8f);
            grade.setGrade('F');
        }
    }
}