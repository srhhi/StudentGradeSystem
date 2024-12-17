package com.example.StudentGradeMS.Controller;

import com.example.StudentGradeMS.Model.Lecturer;
import com.example.StudentGradeMS.Model.Subject;
import com.example.StudentGradeMS.Service.Service.LecturerService;
import com.example.StudentGradeMS.Service.Service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private LecturerService lecturerService;

    @GetMapping("/api")
    @ResponseBody
    public List<Subject> getAllSubjects(){
        List<Subject> subjects = subjectService.getAllSubjects();
        return ResponseEntity.ok(subjects).getBody();
    }

    @GetMapping
    public String listSubjects(Model model) {
        List<Subject> subjects = subjectService.getAllSubjects();
        model.addAttribute("subjects", subjects);
        return "subject/index";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("subject", new Subject());

        // Fetch lecturer names from the database
        List<String> lecturerNames = lecturerService.getAllLecturers()
                .stream()
                .map(Lecturer::getName)
                .toList();
        model.addAttribute("lecturerNames", lecturerNames);
        return "subject/add";
    }

    @PostMapping("/add")
    public String addSubject(@ModelAttribute Subject subject) {
        subjectService.createSubject(subject);
        return "redirect:/subject";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Subject subject = subjectService.getSubjectById(id);
        model.addAttribute("subject", subject);

        // Fetch lecturer names
        List<String> lecturerNames = lecturerService.getAllLecturers()
                .stream()
                .map(Lecturer::getName)
                .toList();
        model.addAttribute("lecturerNames", lecturerNames);
        return "subject/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateSubject(@PathVariable Long id, @ModelAttribute Subject subject) {
        subjectService.updateSubject(id, subject);
        return "redirect:/subject";
    }

    @DeleteMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubject(id);
        return ResponseEntity.noContent().build();
    }

}
