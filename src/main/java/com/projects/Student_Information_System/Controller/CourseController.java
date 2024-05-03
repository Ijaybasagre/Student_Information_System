package com.projects.Student_Information_System.Controller;

import com.projects.Student_Information_System.Model.DTO.CourseDTO;
import com.projects.Student_Information_System.Model.Student;
import com.projects.Student_Information_System.Service.CourseService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    //Course Management
    @GetMapping
    public ResponseEntity getAllCourses() {
        return new ResponseEntity(courseService.getAllCourses(), HttpStatus.OK);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity getCourse(@PathVariable Long courseId) {
        return new ResponseEntity(courseService.getCourse(courseId), HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity createCourse(@RequestBody CourseDTO courseDTO) {
        return new ResponseEntity(courseService.createCourse(courseDTO), HttpStatus.OK);
    }

    @PutMapping("/update/{courseId}")
    public ResponseEntity updateCourse(@PathVariable Long courseId,
                                       @RequestBody CourseDTO courseDTO) {
        return new ResponseEntity(courseService.updateCourse(courseId, courseDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{courseId}")
    public ResponseEntity deleteCourse(@PathVariable Long courseId) {
        courseService.deleteCourse(courseId);
        return new ResponseEntity(HttpStatus.OK);
    }

    //Student Enrollment
    @GetMapping("/course/{courseId}/students")
    public ResponseEntity getCourseStudents(@PathVariable Long courseId) {
        return new ResponseEntity(courseService.getCourseStudent(courseId), HttpStatus.OK);
    }

    @PutMapping("/enroll")
    public ResponseEntity enrollStudentToCourse(@RequestParam Long courseId
            , @RequestParam Long studentId) {
        return new ResponseEntity(courseService.addStudent(courseId, studentId), HttpStatus.OK);
    }

    @DeleteMapping("/remove")
    public ResponseEntity removeStudentToCourse(@RequestParam Long courseId
            , @RequestParam Long studentId) {
        return new ResponseEntity(courseService.removeStudent(courseId, studentId), HttpStatus.OK);
    }

    //Instructor Assignment
    @GetMapping("/course/{courseId}/instructors")
    public ResponseEntity getCourseInstructors(@PathVariable Long courseId) {
        return new ResponseEntity(courseService.getCourseInstructors(courseId), HttpStatus.OK);
    }

    @PutMapping("/instructor/assign")
    public ResponseEntity assignInstructorToCourse(@RequestParam Long courseId
            , @RequestParam Long instructorId) {
        return new ResponseEntity(courseService.addInstructor(courseId, instructorId), HttpStatus.OK);
    }

    @DeleteMapping("/instructor/remove")
    public ResponseEntity removeInstructorToCourse(@RequestParam Long courseId
            , @RequestParam Long instructorId) {
        return new ResponseEntity(courseService.removeInstructor(courseId, instructorId), HttpStatus.OK);
    }

}
