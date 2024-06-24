package com.projects.Student_Information_System.Controller;

import com.projects.Student_Information_System.Model.DTO.CourseDTO;
import com.projects.Student_Information_System.Model.Response;
import com.projects.Student_Information_System.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Response getAllCourses(@RequestParam int page, @RequestParam int size) {
        Response response = new Response();
        response.setData(courseService.getAllCourses(page, size));

        return response;
    }

    @GetMapping("/course/{courseId}")
    public Response getCourse(@PathVariable Long courseId) {
        Response response = new Response();
        response.setData(courseService.getCourse(courseId));
        return response;
    }


    @PostMapping("/create")
    public Response createCourse(@RequestBody CourseDTO courseDTO) {
        Response response = new Response();
        response.setData(courseService.createCourse(courseDTO));
        return response;
    }

    @PutMapping("/update/{courseId}")
    public Response updateCourse(@PathVariable Long courseId,
                                 @RequestBody CourseDTO courseDTO) {
        Response response = new Response();
        response.setData(courseService.updateCourse(courseId, courseDTO));
        return response;
    }

    @DeleteMapping("/delete/{courseId}")
    public Response deleteCourse(@PathVariable Long courseId) {
        courseService.deleteCourse(courseId);
        Response response = new Response();
        response.setMessage("Deleted Successfully");
        return response;
    }
}
