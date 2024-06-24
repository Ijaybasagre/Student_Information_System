package com.projects.Student_Information_System.Controller;

import com.projects.Student_Information_System.Model.Response;
import com.projects.Student_Information_System.Service.CourseAdministrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course-management")
public class CourseAdministrationController {

    private final CourseAdministrationService courseAdministrationService;

    @Autowired
    public CourseAdministrationController(CourseAdministrationService courseAdministrationService) {
        this.courseAdministrationService = courseAdministrationService;
    }

    @GetMapping("/{courseId}/students")
    public Response getCourseStudents(@PathVariable Long courseId) {
        Response response = new Response();
        response.setData(courseAdministrationService.getCourseStudent(courseId));
        return response;
    }

    @GetMapping("/{courseId}/instructors")
    public Response getCourseInstructors(@PathVariable Long courseId) {
        Response response = new Response();
        response.setData(courseAdministrationService.getCourseInstructors(courseId));
        return response;
    }

    @PutMapping("/enroll-student")
    public Response enrollStudent(@RequestParam Long courseId, @RequestParam Long studentId) {
        Response response = new Response();
        response.setMessage( courseAdministrationService.addStudent(courseId, studentId));
        return response;
    }

    @PutMapping("/remove-student")
    public Response removeStudent(@RequestParam Long courseId, @RequestParam Long studentId) {
        Response response = new Response();
        courseAdministrationService.removeStudent(courseId, studentId);
        response.setMessage("Student removed successfully");
        return response;
    }

    @PutMapping("/assign-instructor")
    public Response assignInstructor(@RequestParam Long courseId, @RequestParam Long instructorId) {
        Response response = new Response();
        courseAdministrationService.addInstructor(courseId, instructorId);
        response.setMessage("Instructor assigned successfully");
        return response;
    }

    @PutMapping("/remove-instructor")
    public Response removeInstructor(@RequestParam Long courseId, @RequestParam Long instructorId) {
        Response response = new Response();
        courseAdministrationService.removeInstructor(courseId, instructorId);
        response.setMessage("Instructor removed successfully");
        return response;
    }

    @GetMapping("/grades/{studentId}")
    public Response getStudentGrades(@PathVariable Long studentId) {
        Response response = new Response();
        response.setData(courseAdministrationService.getStudentGrades(studentId));
        return response;
    }

    @GetMapping("/grades/{studentId}/rating")
    public Response getStudentRating(@PathVariable Long studentId) {
        Response response = new Response();
        response.setData(courseAdministrationService.getStudentRating(studentId));
        return response;
    }

    @GetMapping("/grades/{studentId}/report")
    public Response getStudentStatusReport(@PathVariable Long studentId) {
        Response response = new Response();
        response.setData(courseAdministrationService.getStudentStatusReport(studentId));
        return response;
    }

    @PostMapping("/grades/insert")
    public Response addGrade(@RequestParam Long studentId,
                             @RequestParam Long courseId,
                             @RequestParam Double gradeValue) {
        courseAdministrationService.addGrade(studentId, courseId, gradeValue);
        Response response = new Response();
        response.setMessage("Created Successfully");
        return response;
    }

    @PutMapping("/grades/update")
    public Response updateGrade(@RequestParam Long studentId,
                                @RequestParam Long courseId,
                                @RequestParam Double gradeValue) {
        courseAdministrationService.updateGrade(studentId, courseId, gradeValue);
        Response response = new Response();
        response.setMessage("Updated Successfully");
        return response;
    }
}
