package com.projects.Student_Information_System.Service;

import com.projects.Student_Information_System.Model.DTO.InstructorDTO;
import com.projects.Student_Information_System.Model.DTO.StudentDTO;
import com.projects.Student_Information_System.Model.Instructor;
import com.projects.Student_Information_System.Model.Student;
import com.projects.Student_Information_System.Repository.IInstructorRepository;
import com.projects.Student_Information_System.Repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstructorService {
    private final IInstructorRepository iInstructorRepository;

    @Autowired
    public InstructorService(IInstructorRepository iInstructorRepository) {
        this.iInstructorRepository = iInstructorRepository;
    }

    public List<InstructorDTO> getAllInstructor() {
        return iInstructorRepository.findAll()
                .stream()
                .map(this::convertInstructorEntityToDTO)
                .collect(Collectors.toList());
    }

    public InstructorDTO getInstructor(Long id) {
        return convertInstructorEntityToDTO(findInstructorById(id));
    }

    @Transactional
    public InstructorDTO createInstructor(InstructorDTO instructorDTO) {
        var instructor  = convertInstructorDTOToEntity(instructorDTO);
        return convertInstructorEntityToDTO(iInstructorRepository.save(instructor));
    }
    @Transactional
    public InstructorDTO updateInstructor(Long id, InstructorDTO instructorDTO) {
        var instructorToUpdate = findInstructorById(id);
        instructorToUpdate.setPersonalInformation(instructorDTO.getPersonalInformation());
        instructorToUpdate.setContactInformation(instructorDTO.getContactInformation());
        return convertInstructorEntityToDTO(iInstructorRepository.save(instructorToUpdate));
    }

    @Transactional
    public void deleteInstructor(Long id) {
        var studentToDelete = findInstructorById(id);
        iInstructorRepository.deleteById(studentToDelete.getId());
    }

    public Instructor findInstructorById(Long id) {
        return iInstructorRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Instructor id: "+id + " Doesn't exist")
                );
    }

    public InstructorDTO convertInstructorEntityToDTO(Instructor instructor) {
        return InstructorDTO.builder()
                .id(instructor.getId())
                .personalInformation(instructor.getPersonalInformation())
                .contactInformation(instructor.getContactInformation())
                .build();
    }

    public Instructor convertInstructorDTOToEntity(InstructorDTO instructorDTO) {
     return Instructor.builder()
                     .personalInformation(instructorDTO.getPersonalInformation())
                             .contactInformation(instructorDTO.getContactInformation())
             .build();
    }
}
