package com.example.StudentGradeMS.Service.Service;

import com.example.StudentGradeMS.Repository.StudentRepository;
import com.example.StudentGradeMS.Model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    // Retrieves a list of all students from the repository
    public List<Student>getAllStudents(){
        return studentRepository.findAll();
    }

    // Retrieves a student by their student number, throws an exception if not found
    public Student getStudentById(int studentNo){
        return studentRepository.findById(studentNo).orElseThrow(() -> new RuntimeException("Student not found with studentNo " + studentNo));
    }

    // Creates a new student record in the repository
    public Student createStudent(Student student) {
        try {
            return studentRepository.save(student);
        } catch (Exception e) {
            // Log the exception details
            System.out.println("Error saving student: " + e.getMessage());
            throw e; // Rethrow for handling in controller
        }
    }

    // Updates an existing student record in the repository
    public Student updateStudent(int studentNo, Student studentDetails){
        Student student= studentRepository.findById(studentNo).orElseThrow(() -> new RuntimeException("Student not found with studentNo " + studentNo));
        if (student != null){
            student.setStudentNo(studentDetails.getStudentNo());
            student.setName(studentDetails.getName());
            student.setFaculty(studentDetails.getFaculty());
            student.setCourse(studentDetails.getCourse());
            return studentRepository.save(student);
        }
        return null;
    }

    // Deletes a student record by their student number from the repository
    public void deleteStudent(int studentNo){
        studentRepository.deleteById(studentNo);
    }
}
