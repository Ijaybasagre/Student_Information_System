package com.projects.Student_Information_System.Controller;

import com.projects.Student_Information_System.Model.DTO.StudentDTO;
import com.projects.Student_Information_System.Model.Student;
import com.projects.Student_Information_System.Service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @GetMapping
    public ResponseEntity findAllStudent() {
        return new ResponseEntity(studentService.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity findStudent(@PathVariable Long studentId) {
        return new ResponseEntity(studentService.getStudent(studentId), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity addStudent(@RequestBody @Valid StudentDTO studentDTO) {
        return new ResponseEntity(studentService.createStudent(studentDTO),HttpStatus.CREATED);
    }

    @PutMapping("/update/{studentId}")
    public ResponseEntity updateStudent(@PathVariable Long studentId,
                                         @RequestBody @Valid StudentDTO studentDTO) {
        return new ResponseEntity(studentService.updateStudent(studentId,studentDTO),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
        return new ResponseEntity(HttpStatus.OK);
    }


}
