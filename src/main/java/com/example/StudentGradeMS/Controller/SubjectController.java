package com.example.StudentGradeMS.Controller;

import com.example.StudentGradeMS.Model.Subject;
import com.example.StudentGradeMS.Service.Service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/api")
    @ResponseBody
    public List<Subject> getAllSubjects(){
       List<Subject> subjects = subjectService.getAllSubjects();
       return ResponseEntity.ok(subjects).getBody();
    }

    @GetMapping
    public String listSubjects(Model model){
        List<Subject> subjects = subjectService.getAllSubjects();
        model.addAttribute("subject", subjects);
        return  "subject/index";
    }

    @GetMapping("/api/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable Long id){
        Subject subject = subjectService.getSubjectById(id);
        return new ResponseEntity<>(subject, HttpStatus.OK);
    }

    @GetMapping("/add")
    public String showAddForm(Model model){
        model.addAttribute("subject", new Subject());
        model.addAttribute("lecturerNames", List.of("Dr. Smith", "Dr. Jane Doe", "Dr. John Brown"));
        return "subject/add";
    }

    @PostMapping("/api")
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject){
        Subject createdSubject = subjectService.createSubject(subject);
        return new ResponseEntity<>(createdSubject, HttpStatus.CREATED);
    }

    @PostMapping("/add")
    public String addSubject(@ModelAttribute Subject subject){
        subjectService.createSubject(subject);
        return "redirect:/subject";
    }

    @PutMapping("/api/{id}")
    public ResponseEntity<Subject> updateSubjectApi(@PathVariable Long id, @RequestBody Subject subjectDetails){
        Subject updatedSubject = subjectService.updateSubject(id, subjectDetails);
        return new ResponseEntity<>(updatedSubject, HttpStatus.OK);
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Subject subject = subjectService.getSubjectById(id);
        model.addAttribute("subject", subject);
        model.addAttribute("lecturerNames", List.of("Dr. Smith", "Dr. Jane Doe", "Dr. John Brown"));
        return "subject/edit";
    }


    // Handle the form submission to update a subject
    @PostMapping("/edit/{id}")
    public String updateSubject(@PathVariable Long id, @ModelAttribute Subject subject) {
        subjectService.updateSubject(id, subject);
        return "redirect:/subject";
    }

    @DeleteMapping("/api/{id}")
    public ResponseEntity<Subject> deleteSubject(@PathVariable Long id){
        subjectService.deleteSubject(id);
        return ResponseEntity.noContent().build();
    }

}
