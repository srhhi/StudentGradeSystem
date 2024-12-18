package com.example.StudentGradeMS.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Grade extends Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private char grade;
    private String status;
    private float gpa;
    //studentNo
    private int studentNo;

    // Getter and Setter for id
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    // Getter and Setter for grade
    public char getGrade() {
        return grade;
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }

    // Getter and Setter for status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Getter and Setter for gpa
    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    public int getStudentNo(){return studentNo;}

    public void setStudentNo(int studentNo){this.studentNo = studentNo;}
}