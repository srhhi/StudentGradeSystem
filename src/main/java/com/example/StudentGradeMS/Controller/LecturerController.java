package com.example.StudentGradeMS.Controller;

import com.example.StudentGradeMS.Model.Lecturer;
import com.example.StudentGradeMS.Service.Service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lecturer")
public class LecturerController {
    @Autowired
    private LecturerService lecturerService;

    @GetMapping("/api")
    @ResponseBody
    public List<Lecturer>getAllLecturers(){
        List<Lecturer> lecturers = lecturerService.getAllLecturers();
        return ResponseEntity.ok(lecturers).getBody();
    }

    @GetMapping
    public String listLecturer(Model model){
        List<Lecturer>lecturers=lecturerService.getAllLecturers();
        model.addAttribute("lecturer",lecturers);
        return "lecturer/index";
    }

    @GetMapping("/api/{name}")
    public ResponseEntity<Lecturer> getLecturerByName(@PathVariable String name){
        Lecturer lecturer = lecturerService.getLecturerByName(name);
        return new ResponseEntity<>(lecturer, HttpStatus.OK);
    }

    @GetMapping("/add")
    public String showAddForm(Model model){
        model.addAttribute("lecturer",new Lecturer());
       // model.addAttribute("faculty", List.of("Engineering","Education","Chemical"));
        return "lecturer/add";
    }

    @PostMapping("/api")
    public ResponseEntity<Lecturer> createLecturer(@RequestBody Lecturer lecturer){
        Lecturer createdLecturer =  lecturerService.createLecturer(lecturer);
        return new ResponseEntity<>(createdLecturer,HttpStatus.CREATED);
    }

    @PostMapping("/add")
    public String addLecturer(@ModelAttribute Lecturer lecturer){
        lecturerService.createLecturer(lecturer);
        return "redirect:/lecturer";
    }
    @PutMapping("/api/{name}")
    public ResponseEntity<Lecturer> updateLecturerApi(@PathVariable String name, @RequestBody Lecturer lecturerDetails){
        Lecturer updatedLecturer = lecturerService.updateLecturer(name,lecturerDetails);
        return new ResponseEntity<>(updatedLecturer, HttpStatus.OK);
    }
    @GetMapping("/edit/{name}")
    public String showEditForm(@PathVariable String name, Model model){
        Lecturer lecturer=lecturerService.getLecturerByName(name);
        model.addAttribute("lecturer",lecturer);
        //model.addAttribute("faculty", List.of("Engineering","Education","Chemical"));
        return "lecturer/edit";
    }

    @PostMapping("/edit/{name}")
    public String updateLecturer(@PathVariable String name,@ModelAttribute Lecturer lecturer){
        lecturerService.updateLecturer(name,lecturer);
        return "redirect:/lecturer";
    }

    @DeleteMapping("/api/{name}")
    public ResponseEntity<Lecturer> deleteLecturer(@PathVariable String name){
        lecturerService.deleteLecturer(name);
        return ResponseEntity.noContent().build();
    }
}
