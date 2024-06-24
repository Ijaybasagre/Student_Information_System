package com.projects.Student_Information_System.Controller;

import com.projects.Student_Information_System.Model.DTO.StudentDTO;
import com.projects.Student_Information_System.Model.Response;
import com.projects.Student_Information_System.Service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping
    public Response findAllStudent(int page, int size) {
        Response response = new Response();
        response.setData(studentService.getAllStudents(page, size));
        return response;
    }

    @GetMapping("/student/{studentId}")
    public Response findStudent(@PathVariable Long studentId) {
        Response response = new Response();
        response.setData(studentService.getStudent(studentId));
        return response;
    }

    @GetMapping("/student/{studentId}/courses")
    public Response findStudentSubjects(@PathVariable Long studentId) {
        Response response = new Response();
        response.setData(studentService.getStudentSubjects(studentId));
        return response;
    }

    @PostMapping("/create")
    public Response addStudent(@RequestBody @Valid StudentDTO studentDTO) {
        Response response = new Response();
        response.setData(studentService.createStudent(studentDTO));
        return response;
    }

    @PutMapping("/update/{studentId}")
    public Response updateStudent(@PathVariable Long studentId,
                                  @RequestBody @Valid StudentDTO studentDTO) {
        Response response = new Response();
        response.setData(studentService.updateStudent(studentId, studentDTO));
        return response;
    }

    @DeleteMapping("/delete/{studentId}")
    public Response deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
        Response response = new Response();
        response.setMessage("Student Deleted Successfully");
        return response;
    }


}
