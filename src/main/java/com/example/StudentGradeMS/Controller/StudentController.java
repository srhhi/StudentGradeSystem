package com.example.StudentGradeMS.Controller;

import com.example.StudentGradeMS.Repository.StudentRepository;
import com.example.StudentGradeMS.Service.Interface.IStudentService;
import com.example.StudentGradeMS.Model.Student;
import com.example.StudentGradeMS.Service.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student>getAllStudents(){
        return studentService.getAllStudents();
    }
    @GetMapping("/{studentNo}")
    public Student getStudentById(@PathVariable int studentNo){
        return studentService.getStudentById(studentNo);
    }
    @PostMapping
    public Student createStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }
    @PutMapping("/{studentNo}")
    public Student updateStudent(@PathVariable int studentNo, @RequestBody Student student){
        return studentService.updateStudent(studentNo,student);
    }
    @DeleteMapping("/{studentNo}")
    public void deleteStudent(@PathVariable int studentNo){
        studentService.deleteStudent(studentNo);
    }

}
