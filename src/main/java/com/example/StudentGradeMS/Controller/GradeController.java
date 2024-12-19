package com.example.StudentGradeMS.Controller;

import com.example.StudentGradeMS.Model.Grade;
import com.example.StudentGradeMS.Model.Student;
import com.example.StudentGradeMS.Model.Subject;
import com.example.StudentGradeMS.Service.Service.GradeService;
import com.example.StudentGradeMS.Service.Service.StudentService;
import com.example.StudentGradeMS.Service.Service.SubjectService;
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
    @Autowired
    private StudentService studentService;
    @Autowired
    private SubjectService subjectService;

    @GetMapping("/api")
    @ResponseBody
    public List<Grade> getAllGrades() {
        return gradeService.getAllGrades();
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Grade> getGradeById(@PathVariable long id) {
        Grade grade = gradeService.getGradeById(id);
        if (grade == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(grade);
    }

    @PostMapping("/api")
    @ResponseBody
    public ResponseEntity<Grade> createGrade(@RequestBody Grade grade) {
        try {
            Grade createdGrade = gradeService.createGrade(grade);
            return new ResponseEntity<>(createdGrade, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Grade> updateGradeApi(@PathVariable long id, @RequestBody Grade gradeDetails) {
        try {
            Grade updatedGrade = gradeService.updateGrade(id, gradeDetails);
            return new ResponseEntity<>(updatedGrade, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<HttpStatus> deleteGrade(@PathVariable long id) {
        try {
            gradeService.deleteGrade(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //---------------------------------------------------////---------------------------------------------------//

    // View Endpoint: List all grade
    @GetMapping("/index")
    public String listGrades(Model model){
        List<Grade> grades = gradeService.getAllGrades();
        model.addAttribute("grades",grades);
        model.addAttribute("pageContent", "grade/index");
        return "index";
    }

    // View Endpoint: Show add form
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("grade", new Grade());
        model.addAttribute("pageContent", "grade/add");
        List<Student> students = studentService.getAllStudents(); // Fetch all students
        model.addAttribute("studentNames", students); // Add students to the model
        List<Subject> subjects = subjectService.getAllSubjects(); // Fetch all subjects
        model.addAttribute("subjectNames", subjects); // Add students to the model
        return "index";
    }

    // View Endpoint: Handle add form submission
    @PostMapping("/add")
    public String addGrade(@ModelAttribute Grade grades){
        gradeService.calculateGradeDetails(grades);
        gradeService.createGrade(grades);
        return "redirect:/grade/index";
    }

    // View Endpoint: Show edit form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable long id, Model model) {
        Grade grade = gradeService.getGradeById(id);
        model.addAttribute("grade", grade);
        model.addAttribute("pageContent", "grade/edit");
        List<Student> students = studentService.getAllStudents(); // Fetch all students
        model.addAttribute("studentNames", students); // Add students to the model
        List<Subject> subjects = subjectService.getAllSubjects(); // Fetch all subjects
        model.addAttribute("subjectNames", subjects); // Add students to the model
        return "index";
    }

    // View Endpoint: Handle edit form submission
    @PostMapping("/edit/{id}")
    public String updateGrade(@PathVariable long id, @ModelAttribute Grade grade) {
        gradeService.calculateGradeDetails(grade);
        gradeService.updateGrade(id, grade);
        return "redirect:/grade/index";
    }

}