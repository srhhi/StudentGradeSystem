package com.example.StudentGradeMS.Repository;

import com.example.StudentGradeMS.Model.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer,Long> {
}
