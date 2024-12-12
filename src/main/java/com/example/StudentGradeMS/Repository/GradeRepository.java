package com.example.StudentGradeMS.Repository;

import com.example.StudentGradeMS.Model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository  extends JpaRepository<Grade, Long> {
}
