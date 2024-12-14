package com.example.StudentGradeMS.Controller;

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

    @GetMapping("/api")
    @ResponseBody
    public List<Student>getAllStudent(){
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students).getBody();
    }//View all students list

    @GetMapping
    public String listStudent(Model model){
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("student",students);
        return "student/index";
    }

    @GetMapping("/api/{studentNo}")
    public ResponseEntity<Student> getStudentById(@PathVariable int studentNo){
        Student student = studentService.getStudentById(studentNo);
        return new ResponseEntity<>(student, HttpStatus.OK);
    } //View student by ID

    @GetMapping("/add")
    public String showAddForm(Model model){
        model.addAttribute("student", new Student());
       // model.addAttribute("name", List.of("Johnny Doe","Christonfer","Caleb"));
        return "student/add";
    }

    @PostMapping("/api")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
        return new ResponseEntity<>(createdStudent,HttpStatus.CREATED);
    }   //Create a new Student

    @PostMapping("/add")
    public String addStudent(@ModelAttribute Student student){
        studentService.createStudent(student);
        return "redirect:/student";
    }

    @PutMapping("/api/{studentNo}")
    public ResponseEntity<Student> updateStudentApi(@PathVariable int studentNo, @RequestBody Student studentDetails) {
        Student updatedStudent = studentService.updateStudent(studentNo,studentDetails);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }//Updating an existing student
    @GetMapping("/edit/{studentNo}")
    public String showEditForm(@PathVariable int studentNo , Model model){
        Student student= studentService.getStudentById(studentNo);
        model.addAttribute("student",student);
        //model.addAttribute("name",List.of("Johnny Doe","Chris Evans","Caleb"));
        return "student/edit";
    }

    @PostMapping("/edit/{studentNo}")
    public String updateStudent(@PathVariable int studentNo, @ModelAttribute Student student){
        studentService.updateStudent(studentNo,student);
        return "redirect:/student";
    }

    @DeleteMapping("/api/{studentNo}")
    public ResponseEntity<Student> deleteStudent(@PathVariable int studentNo) {
        studentService.deleteStudent(studentNo);
        return ResponseEntity.noContent().build();
    }   //Delete a student
}
