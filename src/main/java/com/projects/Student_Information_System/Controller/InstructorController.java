package com.projects.Student_Information_System.Controller;

import com.projects.Student_Information_System.Model.DTO.InstructorDTO;
import com.projects.Student_Information_System.Model.Response;
import com.projects.Student_Information_System.Service.InstructorService;
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
@RequestMapping("/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }


    @GetMapping
    public Response findAllInstructor(int page, int size) {
        Response response = new Response();
        response.setData(instructorService.getAllInstructor(page, size));
        return response;
    }

    @GetMapping("/instructor/{instructorId}")
    public Response findInstructor(@PathVariable Long instructorId) {
        Response response = new Response();
        response.setData(instructorService.getInstructor(instructorId));
        return response;
    }

    @PostMapping("/create")
    public Response addInstructor(@RequestBody @Valid InstructorDTO instructorDTO) {
        Response response = new Response();
        response.setData(instructorService.createInstructor(instructorDTO));
        return response;
    }

    @PutMapping("/update/{instructorId}")
    public Response updateInstructor(@PathVariable Long instructorId,
                                     @RequestBody @Valid InstructorDTO instructorDTO) {
        Response response = new Response();
        response.setData(instructorService.updateInstructor(instructorId, instructorDTO));
        return response;
    }

    @DeleteMapping("/delete/{instructorId}")
    public Response deleteInstructor(@PathVariable Long instructorId) {
        instructorService.deleteInstructor(instructorId);
        Response response = new Response();
        response.setMessage("Deleted Successfully");
        return response;
    }
}
