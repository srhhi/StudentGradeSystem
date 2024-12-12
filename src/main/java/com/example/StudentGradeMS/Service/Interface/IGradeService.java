package com.example.StudentGradeMS.Service.Interface;


import com.example.StudentGradeMS.Model.Grade;
import java.util.List;
import java.util.Optional;
public interface IGradeService {


    Grade saveGrade(Grade grade);

    List<Grade> getAllGrades();

    Optional<Grade> getGradeById(Long id);

    Grade updateGrade(Long id, Grade grade);

    void deleteGrade(Long id);
}
