package com.example.StudentGradeMS.Controller;

import com.example.StudentGradeMS.Repository.LecturerRepository;
import com.example.StudentGradeMS.Service.Interface.ILecturerService;
import com.example.StudentGradeMS.User.Lecturer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lecturer")
public class LecturerController {
    @Autowired
    private ILecturerService lecturerService;

    @GetMapping
    public List<Lecturer>getAllLecturers(){
        return lecturerService.getAllLecturers();
    }
    @GetMapping("/{name}")
    public Lecturer getLecturerByName(@PathVariable String name){
        return lecturerService.getLecturerByName(name);
    }
    @PostMapping
    public Lecturer createLecturer(@RequestBody Lecturer lecturer){
        return lecturerService.createLecturer(lecturer);
    }
    @PutMapping("/{name}")
    public Lecturer updateLecturer(@PathVariable String name,@RequestBody Lecturer lecturer){
        return lecturerService.updateLecturer(name,lecturer);
    }
    @DeleteMapping("/{name}")
    public void deleteLecturer(@PathVariable String name){
        lecturerService.deleteLecturer(name);
    }
}
