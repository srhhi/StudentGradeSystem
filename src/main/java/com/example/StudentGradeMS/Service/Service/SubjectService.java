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

    // Fetch all subjects
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    // Fetch a specific subject by ID
    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found with id: " + id));
    }

    // Create a new subject
    public Subject createSubject(Subject subject) {
        if (subject.getCreditHours() < 0) {
            throw new ValidationException("Invalid Credit Hour: Credit hours cannot be negative.");
        }
        return subjectRepository.save(subject);
    }

    public Subject updateSubject(Long id, Subject subjectDetails) {
        Subject existingSubject = subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found with id " + id));

        if (subjectDetails.getCreditHours() < 0) {
            throw new ValidationException("Invalid Credit Hour: Credit hours cannot be negative.");
        }

        existingSubject.setCode(subjectDetails.getCode());
        existingSubject.setName(subjectDetails.getName());
        existingSubject.setCreditHours(subjectDetails.getCreditHours());
        existingSubject.setLecturerName(subjectDetails.getLecturerName());

        return subjectRepository.save(existingSubject);
    }

    // Delete a subject
    public void deleteSubject(Long id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found with id " + id));
        subjectRepository.delete(subject);
    }
}
