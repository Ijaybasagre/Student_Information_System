package com.projects.Student_Information_System.Controller;

import com.projects.Student_Information_System.Model.DTO.InstructorDTO;
import com.projects.Student_Information_System.Model.DTO.StudentDTO;
import com.projects.Student_Information_System.Service.InstructorService;
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
@RequestMapping("/instructors")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;


    @GetMapping
    public ResponseEntity findAllStudent() {
        return new ResponseEntity(instructorService.getAllInstructor(), HttpStatus.OK);
    }

    @GetMapping("/instructor/{instructorId}")
    public ResponseEntity findStudent(@PathVariable Long instructorId) {
        return new ResponseEntity(instructorService.getInstructor(instructorId), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity addStudent(@RequestBody @Valid InstructorDTO instructorDTO) {
        return new ResponseEntity(instructorService.createInstructor(instructorDTO),HttpStatus.CREATED);
    }

    @PutMapping("/update/{instructorId}")
    public ResponseEntity updateStudent(@PathVariable Long instructorId,
                                         @RequestBody @Valid InstructorDTO instructorDTO) {
        return new ResponseEntity(instructorService.updateInstructor(instructorId,instructorDTO),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{instructorId}")
    public ResponseEntity deleteStudent(@PathVariable Long instructorId) {
        instructorService.deleteInstructor(instructorId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
