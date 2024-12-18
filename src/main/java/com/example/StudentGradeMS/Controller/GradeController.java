package com.example.StudentGradeMS.Controller;

import com.example.StudentGradeMS.Model.Grade;
import com.example.StudentGradeMS.Service.Service.GradeService;
import com.example.StudentGradeMS.Service.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/grade")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    // View endpoint: Display all grades
    @GetMapping("/index")
    public String gradeIndex(Model model) {
        List<Grade> grades = gradeService.getAllGrades();
        model.addAttribute("grades", grades);
        model.addAttribute("pageContent", "grade/index");
        return "index";
    }

    // API endpoint: Get all grades
    @GetMapping("/api")
    @ResponseBody
    public List<Grade> getAllGradesApi() {
        return gradeService.getAllGrades();
    }

    // API endpoint: Get grade by ID
    @GetMapping("/api/{gradeId}")
    @ResponseBody
    public ResponseEntity<Grade> getGradeById(@PathVariable long gradeId) {
        Grade grade = gradeService.getGradeById(gradeId);
        if (grade == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(grade);
    }

    // API endpoint: Create a grade
    @PostMapping("/api")
    @ResponseBody
    public ResponseEntity<Grade> createGradeApi(@RequestBody Grade grade) {
        try {
            Grade createdGrade = gradeService.createGrade(grade);
            return new ResponseEntity<>(createdGrade, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // API endpoint: Update a grade
    @PutMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Grade> updateGradeApi(@PathVariable long id, @RequestBody Grade gradeDetails) {
        try {
            Grade updatedGrade = gradeService.updateGrade(id, gradeDetails);
            if (updatedGrade != null) {
                return new ResponseEntity<>(updatedGrade, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // API endpoint: Delete a grade
    @DeleteMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<HttpStatus> deleteGradeApi(@PathVariable long id) {
        try {
            gradeService.deleteGrade(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // View Endpoint: List all grades
    @GetMapping
    public String listGrades(Model model) {
        List<Grade> grades = gradeService.getAllGrades();
        model.addAttribute("grades", grades);
        return "grades/index";
    }

    // View Endpoint: Show add form
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("grade", new Grade());
        return "grade/add";
    }

    // View Endpoint: Handle add form submission
    @PostMapping("/add")
    public String addGrade(@ModelAttribute Grade grade) {
        gradeService.createGrade(grade);
        return "redirect:/grade";
    }

    // View Endpoint: Show edit form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable long id, Model model) {
        Grade grade = gradeService.getGradeById(id);
        if (grade == null) {
            return "redirect:/grade"; // Redirect if grade is not found
        }
        model.addAttribute("grade", grade);
        return "grade/edit";
    }

    // View Endpoint: Handle edit form submission
    @PostMapping("/edit/{id}")
    public String updateGrade(@PathVariable long id, @ModelAttribute Grade grade) {
        gradeService.updateGrade(id, grade);
        return "redirect:/grade";
    }
}
