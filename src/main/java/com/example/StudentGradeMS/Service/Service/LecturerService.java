package com.example.StudentGradeMS.Service.Service;

import com.example.StudentGradeMS.Model.Lecturer;
import com.example.StudentGradeMS.Repository.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LecturerService{
    @Autowired
    private LecturerRepository lecturerRepository;

    public List<Lecturer> getAllLecturers(){
        return lecturerRepository.findAll();
    }

    public Lecturer getLecturerByName(String name){
        return lecturerRepository.findById(name).orElse(null);
    }
    public Lecturer createLecturer(Lecturer lecturer){
        return lecturerRepository.save(lecturer);
    }
    public Lecturer updateLecturer(String name, Lecturer lecturerDetails){
        Lecturer lecturer= lecturerRepository.findById(name).orElse(null);
        if (lecturer !=null){
            lecturer.setFaculty(lecturerDetails.getFaculty());
            return lecturerRepository.save(lecturer);
        }
        return null;
    }
    public void deleteLecturer(String name){
        lecturerRepository.deleteById(name);
    }

}