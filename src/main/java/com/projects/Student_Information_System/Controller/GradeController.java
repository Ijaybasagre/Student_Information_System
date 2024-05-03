package com.projects.Student_Information_System.Controller;

import com.projects.Student_Information_System.Service.CourseService;
import com.projects.Student_Information_System.Service.GradeService;
import com.projects.Student_Information_System.Service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;

@RestController
@RequestMapping("/grades")
public class GradeController {

    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping("/{studentId}")
    public ResponseEntity getStudentGrades(@PathVariable Long studentId) {
        return new ResponseEntity(gradeService.getStudentGrades(studentId),HttpStatus.OK);
    }
    @GetMapping("/{studentId}/rating")
    public ResponseEntity getStudentRating(@PathVariable Long studentId) {
        return new ResponseEntity(gradeService.getStudentRating(studentId),HttpStatus.OK);
    }

    @GetMapping("/{studentId}/report")
    public ResponseEntity getStudentStatusReport(@PathVariable Long studentId) {
        return new ResponseEntity(gradeService.getStudentStatusReport(studentId),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addGrade(@RequestParam Long studentId,
                                   @RequestParam Long courseId,
                                   @RequestParam Double gradeValue) {
        gradeService.addGrade(studentId,courseId,gradeValue);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updateGrade(@RequestParam Long studentId,
                                      @RequestParam Long courseId,
                                      @RequestParam Double gradeValue) {
        gradeService.updateGrade(studentId,courseId,gradeValue);
        return new ResponseEntity(HttpStatus.OK);
    }



}
