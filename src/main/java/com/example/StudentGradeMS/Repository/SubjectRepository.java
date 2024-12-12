package com.example.StudentGradeMS.Repository;

import com.example.StudentGradeMS.Model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
