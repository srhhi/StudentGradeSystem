package com.example.StudentGradeMS.Repository;

import com.example.StudentGradeMS.Model.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserLogin, String> {
    Optional<UserLogin> findByUsername(String username);
}
