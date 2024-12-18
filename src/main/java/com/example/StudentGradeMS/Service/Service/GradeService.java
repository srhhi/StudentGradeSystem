package com.example.StudentGradeMS.Service.Service;

import com.example.StudentGradeMS.Repository.GradeRepository;
import com.example.StudentGradeMS.Model.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Grade grade = gradeRepository.findById(id).orElse(null);
        if (grade != null) {
            // Update inherited fields (from Subject)
            grade.setCode(newGrade.getCode());
            grade.setName(newGrade.getName());
            grade.setCreditHours(newGrade.getCreditHours());
            grade.setLecturerName(newGrade.getLecturerName());

            // Update Grade-specific fields
            grade.setGrade(newGrade.getGrade());
            grade.setStatus(newGrade.getStatus());
            grade.setGpa(newGrade.getGpa());
            grade.setStudentNo(newGrade.getStudentNo());
            return gradeRepository.save(grade);
        }
        return null;
    }

    // Delete a grade
    public void deleteGrade(Long id) {
        gradeRepository.deleteById(id);
    }
}
