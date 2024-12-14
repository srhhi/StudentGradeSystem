package com.example.StudentGradeMS.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentViewController {

    @GetMapping("/create-student")
    public String showCreateStudentForm() {
        return "create_student";
    }
}
