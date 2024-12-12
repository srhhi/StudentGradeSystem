package com.example.StudentGradeMS.Service.Service;

import com.example.StudentGradeMS.Model.Grade;
import com.example.StudentGradeMS.Repository.GradeRepository;
import com.example.StudentGradeMS.Service.Interface.IGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeService implements IGradeService {
    private final GradeRepository gradeRepository;
    @Autowired
    public GradeService(GradeRepository gradeRepository){
        this.gradeRepository = gradeRepository;
    }
    @Override
    public Grade saveGrade(Grade grade){
        return gradeRepository.save(grade);
    }

    @Override
    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }

    @Override
    public Optional<Grade> getGradeById(Long id) {
        return gradeRepository.findById(id);
    }

    @Override
    public Grade updateGrade(Long id, Grade grade){
        return gradeRepository.findById(id)
                .map(existingGrade -> {
                    existingGrade.setGrade(grade.getGrade());
                    existingGrade.setStatus(grade.getStatus());
                    existingGrade.setGpa(grade.getGpa());
                    return gradeRepository.save(existingGrade);
                })
                .orElseThrow(() -> new RuntimeException("Grade not found with id: " + id));
    }

    @Override
    public void deleteGrade(Long id) {
        gradeRepository.deleteById(id);
    }
}
