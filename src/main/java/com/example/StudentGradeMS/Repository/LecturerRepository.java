package com.example.StudentGradeMS.Repository;

import com.example.StudentGradeMS.User.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer,String> {
}
