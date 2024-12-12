package com.example.StudentGradeMS.Controller;

import com.example.StudentGradeMS.Model.Grade;
import com.example.StudentGradeMS.Service.Service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grades")
public class GradeController {

    private final GradeService gradeService;

    @Autowired
    public GradeController(GradeService gradeService){
        this.gradeService = gradeService;
    }

    @PostMapping
    public ResponseEntity<Grade> createGrade(@RequestBody Grade grade){
        return ResponseEntity.ok(gradeService.saveGrade(grade));
    }

    @GetMapping
    public ResponseEntity<List<Grade>> getAllGrades(){
        return ResponseEntity.ok(gradeService.getAllGrades());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Grade> getGradeById(@PathVariable Long id) {
        return gradeService.getGradeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<Grade> updateGrade(@PathVariable Long id, @RequestBody Grade grade){
        return ResponseEntity.ok(gradeService.updateGrade(id,grade));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteGrade(@PathVariable Long id){
        gradeService.deleteGrade(id);
        return ResponseEntity.noContent().build();
    }
}
