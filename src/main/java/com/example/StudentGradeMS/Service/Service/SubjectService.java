package com.example.StudentGradeMS.Service.Service;

import com.example.StudentGradeMS.Model.Subject;
import com.example.StudentGradeMS.Repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    //Fetch all subjects
    public List<Subject> getAllSubjects(){
        return subjectRepository.findAll();
    }
    // Fetch a specific subject by ID
    public Subject getSubjectById(Long id){
        return subjectRepository.findById(id).orElseThrow(() -> new RuntimeException("Subject not found with id: " + id));

    }
    // Create a new subject
    public Subject createSubject(Subject subject){
        return subjectRepository.save(subject);
    }

    //update an existing subject
    public Subject updateSubject(Long id, Subject subjectDetails){
        Subject existingSubject = subjectRepository.findById(id).orElseThrow(() -> new RuntimeException("Subject not found with id " + id));
        existingSubject.setCode(subjectDetails.getCode());
        existingSubject.setName(subjectDetails.getName());
        existingSubject.setCreditHours(subjectDetails.getCreditHours());
        existingSubject.setLecturerName(subjectDetails.getLecturerName());
        //Tambah lecturer if lecturer siap

        return subjectRepository.save(existingSubject);
    }

    public void deleteSubject(Long id){

        Subject subject = subjectRepository.findById(id).orElseThrow(() -> new RuntimeException("Subject not found with id " + id));
        subjectRepository.delete(subject);
    }

}
