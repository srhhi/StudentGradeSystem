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

    // Retrieves a list of all lecturers from the repository
    public List<Lecturer>getAllLecturers(){
        return lecturerRepository.findAll();
    }

    // Retrieves a lecturer by their ID, throws an exception if not found
    public Lecturer getLecturerById(long id){
        return lecturerRepository.findById(id).orElseThrow(() -> new RuntimeException("Lecturer not found with id " + id));
    }

    // Creates a new lecturer record in the repository
    public Lecturer createLecturer (Lecturer lecturer){
        return lecturerRepository.save(lecturer);
    }

    // Updates an existing lecturer record in the repository
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

    // Deletes a lecturer record by their ID from the repository
    public void deleteLecturer(long id){
        lecturerRepository.deleteById(id);
    }
}
