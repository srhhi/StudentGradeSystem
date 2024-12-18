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
}