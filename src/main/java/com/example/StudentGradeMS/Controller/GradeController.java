package com.example.StudentGradeMS.Controller;

import com.example.StudentGradeMS.Model.Grade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class GradeController {
    //testing purpose only
    @GetMapping("/grade/add")
    public String AddGrade(Model model) {
        return "Grade/Add";
    }
}
