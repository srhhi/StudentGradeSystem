package com.example.StudentGradeMS.Service.Service;

import com.example.StudentGradeMS.Repository.GradeRepository;
import com.example.StudentGradeMS.Model.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService  {
    @Autowired
    private GradeRepository gradeRepository;

    public List<Grade>getAllGrades(){
        return gradeRepository.findAll();
    }
    public Grade getGradesById(long id){
        return gradeRepository.findById(id).orElse(null);
    }
    public Grade createGrade (Grade grade){
        return gradeRepository.save(grade);
    }
    public Grade updateGrade(long id, Grade newGrade){
        Grade grade = gradeRepository.findById(id).orElse(null);
        if (grade != null){
            grade.setGrade(newGrade.getGrade());
            grade.setStatus(newGrade.getStatus());
            grade.setGpa(newGrade.getGpa());
            return gradeRepository.save(grade);
        }
        return null;
    }
    public void deleteGrade(Long id) {
        gradeRepository.deleteById(id);
    }
}
