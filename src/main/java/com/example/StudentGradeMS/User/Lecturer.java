package com.example.StudentGradeMS.User;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Lecturer {

    @Id
    private String name;
    private String faculty;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getFaculty() {
        return faculty;
    }

}
