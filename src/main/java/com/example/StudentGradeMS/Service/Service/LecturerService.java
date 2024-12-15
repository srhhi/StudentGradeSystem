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

    public Lecturer getLecturerById(Long id){
        return lecturerRepository.findById(id).orElse(null);
    }
    public Lecturer createLecturer(Lecturer lecturer){
        return lecturerRepository.save(lecturer);
    }
    public Lecturer updateLecturer(Long id, Lecturer lecturerDetails){
        Lecturer lecturer= lecturerRepository.findById(id).orElse(null);
        if (lecturer !=null){
            lecturer.setName(lecturerDetails.getName());
            lecturer.setFaculty(lecturerDetails.getFaculty());
            return lecturerRepository.save(lecturer);
        }
        return null;
    }
    public void deleteLecturer(Long id){
        lecturerRepository.deleteById(id);
    }

}