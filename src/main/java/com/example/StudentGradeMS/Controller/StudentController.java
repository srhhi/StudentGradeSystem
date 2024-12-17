package com.example.StudentGradeMS.Controller;

import com.example.StudentGradeMS.Model.Grade;
import com.example.StudentGradeMS.Model.Student;
import com.example.StudentGradeMS.Service.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/index")
    public String studentIndex(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        model.addAttribute("pageContent", "student/index");
        return "index";
    }

    @GetMapping
    public String listStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "student/index";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "student/add";
    }

    @PostMapping("/add")
    public String addStudent(@ModelAttribute Student student) {
        studentService.createStudent(student);
        return "redirect:/student";
    }

    @GetMapping("/edit/{studentNo}")
    public String showEditForm(@PathVariable int studentNo, Model model) {
        Student student = studentService.getStudentById(studentNo);
        model.addAttribute("student", student);
        return "student/edit";
    }

    @PostMapping("/edit/{studentNo}")
    public String updateStudent(@PathVariable int studentNo, @ModelAttribute Student student) {
        studentService.updateStudent(studentNo, student);
        return "redirect:/student";
    }

    @GetMapping("/api")
    @ResponseBody
    public List<Student> getAllStudentsApi() {
        return studentService.getAllStudents();
    }

    @GetMapping("/api/{studentNo}")
    @ResponseBody
    public ResponseEntity<Student> getStudentByIdApi(@PathVariable int studentNo) {
        Student student = studentService.getStudentById(studentNo);
        if (student != null) {
            return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api")
    @ResponseBody
    public ResponseEntity<Student> createStudentApi(@RequestBody Student student) {
        try {
            Student createdStudent = studentService.createStudent(student);
            return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/api/{studentNo}")
    @ResponseBody
    public ResponseEntity<Student> updateStudentApi(@PathVariable int studentNo, @RequestBody Student studentDetails) {
        try {
            Student updatedStudent = studentService.updateStudent(studentNo, studentDetails);
            if (updatedStudent != null) {
                return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/api/{studentNo}")
    @ResponseBody
    public ResponseEntity<HttpStatus> deleteStudentApi(@PathVariable int studentNo) {
        try {
            studentService.deleteStudent(studentNo);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
