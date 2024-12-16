package com.example.StudentGradeMS.Controller;

import com.example.StudentGradeMS.Model.Lecturer;
import com.example.StudentGradeMS.Service.Service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/lecturer")
public class LecturerController {

    @Autowired
    private LecturerService lecturerService;

    @GetMapping
    public String listLecturers(Model model) {
        List<Lecturer> lecturers = lecturerService.getAllLecturers();
        model.addAttribute("lecturers", lecturers);
        return "lecturer/index";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("lecturer", new Lecturer());
        return "lecturer/add";
    }

    @PostMapping("/add")
    public String addLecturer(@ModelAttribute Lecturer lecturer) {
        lecturerService.createLecturer(lecturer);
        return "redirect:/lecturer";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Lecturer lecturer = lecturerService.getLecturerById(id);
        model.addAttribute("lecturer", lecturer);
        return "lecturer/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateLecturer(@PathVariable Long id, @ModelAttribute Lecturer lecturer) {
        lecturerService.updateLecturer(id, lecturer);
        return "redirect:/lecturer";
    }

    @GetMapping("/api")
    @ResponseBody
    public List<Lecturer> getAllLecturersApi() {
        return lecturerService.getAllLecturers();
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Lecturer> getLecturerByIdApi(@PathVariable Long id) {
        Lecturer lecturer = lecturerService.getLecturerById(id);
        if (lecturer != null) {
            return new ResponseEntity<>(lecturer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api")
    @ResponseBody
    public ResponseEntity<Lecturer> createLecturerApi(@RequestBody Lecturer lecturer) {
        try {
            Lecturer createdLecturer = lecturerService.createLecturer(lecturer);
            return new ResponseEntity<>(createdLecturer, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Lecturer> updateLecturerApi(@PathVariable Long id, @RequestBody Lecturer lecturerDetails) {
        try {
            Lecturer updatedLecturer = lecturerService.updateLecturer(id, lecturerDetails);
            if (updatedLecturer != null) {
                return new ResponseEntity<>(updatedLecturer, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<HttpStatus> deleteLecturerApi(@PathVariable Long id) {
        try {
            lecturerService.deleteLecturer(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
