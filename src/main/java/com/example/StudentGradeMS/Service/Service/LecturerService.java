package com.example.StudentGradeMS.Service.Service;

import com.example.StudentGradeMS.Model.Lecturer;
import com.example.StudentGradeMS.Repository.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LecturerService {
    @Autowired
    private LecturerRepository lecturerRepository;

    public List<Lecturer>getAllLecturers(){
        return lecturerRepository.findAll();
    }
    public Lecturer getLecturerById(long id){
        return lecturerRepository.findById(id).orElseThrow(() -> new RuntimeException("Lecturer not found with id " + id));
    }
    public Lecturer createLecturer (Lecturer lecturer){
        return lecturerRepository.save(lecturer);
    }
    public Lecturer updateLecturer(long id, Lecturer lecturerDetails){
        Lecturer lecturer= lecturerRepository.findById(id).orElseThrow(() -> new RuntimeException("Lecturer not found with id " + id));
        if (lecturer != null){
            lecturer.setId(lecturerDetails.getId());
            lecturer.setName(lecturerDetails.getName());
            lecturer.setFaculty(lecturerDetails.getFaculty());
            return lecturerRepository.save(lecturer);
        }
        return null;
    }
    public void deleteLecturer(long id){
        lecturerRepository.deleteById(id);
    }
}
