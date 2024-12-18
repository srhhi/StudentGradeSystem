package com.example.StudentGradeMS.Controller;

import com.example.StudentGradeMS.Model.Grade;
import com.example.StudentGradeMS.Service.Service.GradeService;
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

    @GetMapping("/index")
    public String gradeIndex(Model model) {
        List<Grade> grades = gradeService.getAllGrades();
        model.addAttribute("grades", grades);
        model.addAttribute("pageContent", "grade/index");
        return "index";
    }
    @GetMapping("/api")
    @ResponseBody
    public List<Grade> getAllGrade(){
        return gradeService.getAllGrades();
    }

    @GetMapping("/api/{gradeId}")
    @ResponseBody
    public ResponseEntity<Grade> getGradeById(@PathVariable long gradeId) {
        Grade grade = gradeService.getGradesById(gradeId);
        if (grade == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(grade);
    }

    @PostMapping("/api")
    @ResponseBody
    public ResponseEntity<Grade> createGradeApi(@RequestBody Grade grade){
        try{
            Grade createGrade = gradeService.createGrade(grade);
            return  new ResponseEntity<>(createGrade,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Grade> updateGradeApi(@PathVariable long id, @RequestBody Grade gradeDetails) {
        try {
            Grade updateGrade = gradeService.updateGrade(id, gradeDetails);
            if (updateGrade != null) {
                return new ResponseEntity<>(updateGrade, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<HttpStatus> deleteGradeApi(@PathVariable long id) {
        try{
            gradeService.deleteGrade(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //---------------------------------------------------////---------------------------------------------------//

    @GetMapping("/Grade/Index")
    public String grade(Model model) {
        model.addAttribute("page", "grade");
        return "Index :: content"; // Use Thymeleaf fragment for content injection
    }
    // View Endpoint: List all grade
    @GetMapping
    public String listGrades(Model model){
        List<Grade> grades = gradeService.getAllGrades();
        model.addAttribute("grades",grades);
        return "grades/index";
    }

    // View Endpoint: Show add form
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("Grades", new Grade());
        return "grade/add";
    }

    // View Endpoint: Handle add form submission
    @PostMapping("/add")
    public String addGrade(@ModelAttribute Grade grades){
        gradeService.createGrade(grades);
        return "redirect:/grade";
    }

    // View Endpoint: Show edit form
    @GetMapping("/edit/{studentNo}")
    public String showEditForm(@PathVariable long id, Model model) {
        Grade grade = gradeService.getGradesById(id);
        model.addAttribute("grade", grade);
        return "grade/edit";
    }

    // View Endpoint: Handle edit form submission
    @PostMapping("/edit/{studentNo}")
    public String updateGrade(@PathVariable long id, @ModelAttribute Grade grade) {
        gradeService.updateGrade(id, grade);
        return "redirect:/grade";
    }

}