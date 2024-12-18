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

    // Lists all lecturers on the lecturer index page
    @GetMapping("/index")
    public String listLecturers(Model model) {
        List<Lecturer> lecturers = lecturerService.getAllLecturers();
        model.addAttribute("lecturers", lecturers);
        model.addAttribute("pageContent", "lecturer/index");
        return "index";
    }

    // Shows the form for adding a new lecturer
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("lecturer", new Lecturer());
        model.addAttribute("pageContent", "lecturer/add");
        return "index";
    }

    // Handles the form submission for adding a new lecturer
    @PostMapping("/add")
    public String addLecturer(@ModelAttribute Lecturer lecturer) {
        lecturerService.createLecturer(lecturer);
        return "redirect:/lecturer/index";
    }

    // Shows the form for editing an existing lecturer
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Lecturer lecturer = lecturerService.getLecturerById(id);
        model.addAttribute("lecturer", lecturer);
        model.addAttribute("pageContent", "lecturer/edit");
        return "index";
    }

    // Handles the form submission for updating an existing lecturer
    @PostMapping("/edit/{id}")
    public String updateLecturer(@PathVariable Long id, @ModelAttribute Lecturer lecturer) {
        lecturerService.updateLecturer(id, lecturer);
        return "redirect:/lecturer/index";
    }

    // Provides a list of all lecturers via an API endpoint
    @GetMapping("/api")
    @ResponseBody
    public List<Lecturer> getAllLecturersApi() {
        return lecturerService.getAllLecturers();
    }

    // Provides details of a specific lecturer via an API endpoint
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

    // Handles the creation of a new lecturer via an API endpoint
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

    // Handles the update of an existing lecturer via an API endpoint
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

    // Handles the deletion of a lecturer via an API endpoint
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
