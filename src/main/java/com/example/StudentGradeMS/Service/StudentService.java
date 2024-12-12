package com.example.StudentGradeMS.Service;

import com.example.StudentGradeMS.Repository.StudentRepository;
import com.example.StudentGradeMS.User.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student>getAllStudents(){
        return studentRepository.findAll();
    }
    public Student getStudentById(int studentNo){
        return studentRepository.findById(studentNo).orElse(null);
    }
    public Student createStudent (Student student){
        return studentRepository.save(student);
    }
    public Student updateStudent(int studentNo, Student studentDetails){
        Student student= studentRepository.findById(studentNo).orElse(null);

        if (student != null){
            student.setStudentNo(studentDetails.getStudentNo());
            student.setName(studentDetails.getName());
            student.setFaculty(studentDetails.getFaculty());
            student.setCourse(studentDetails.getCourse());
            return studentRepository.save(student);
        }
        return null;
    }
    public void deleteStudent(int studentNo){
        studentRepository.deleteById(studentNo);
    }
}
