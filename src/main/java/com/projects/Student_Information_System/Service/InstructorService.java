package com.projects.Student_Information_System.Service;

import com.projects.Student_Information_System.Model.DTO.InstructorDTO;
import com.projects.Student_Information_System.Model.Enums.Role;
import com.projects.Student_Information_System.Model.Instructor;
import com.projects.Student_Information_System.Repository.IInstructorRepository;
import com.projects.Student_Information_System.Util.IDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public List<InstructorDTO> getAllInstructor(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return iInstructorRepository.findAll(pageable)
                .stream()
                .map(this::convertInstructorEntityToDTO)
                .toList();
    }

    public InstructorDTO getInstructor(Long id) {
        return convertInstructorEntityToDTO(findInstructorById(id));
    }

    @Transactional
    public InstructorDTO createInstructor(InstructorDTO instructorDTO) {
        var instructor = convertInstructorDTOToEntity(instructorDTO);
        instructor.setInstructorId(
                IDGenerator.generate(getLatestCount(),
                        Role.INSTRUCTOR)
        );
        instructor.setRole(Role.INSTRUCTOR);
        return convertInstructorEntityToDTO(iInstructorRepository.save(instructor));
    }

    @Transactional
    public InstructorDTO updateInstructor(Long id, InstructorDTO instructorDTO) {
        var instructorToUpdate = findInstructorById(id);
        instructorToUpdate.setPersonalInformation(instructorDTO.getPersonalInformation());
        return convertInstructorEntityToDTO(iInstructorRepository.save(instructorToUpdate));
    }

    @Transactional
    public void deleteInstructor(Long id) {
        var instructorToDelete = findInstructorById(id);
        if (!instructorToDelete.getAssignedCourses().isEmpty()) {
            throw new IllegalArgumentException("Instructor is assigned to a course");
        }
        iInstructorRepository.deleteById(instructorToDelete.getId());
    }

    public Instructor findInstructorById(Long id) {
        return iInstructorRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Instructor id: " + id + " Doesn't exist")
                );
    }

    private Long getLatestCount() {
        List<Instructor> instructors = iInstructorRepository.findAll();
        return instructors
                .stream()
                .mapToLong(Instructor::getId).max().orElse(0L);
    }

    public InstructorDTO convertInstructorEntityToDTO(Instructor instructor) {
        return InstructorDTO.builder()
                .id(instructor.getId())
                .instructorId(instructor.getInstructorId())
                .role(instructor.getRole())
                .personalInformation(instructor.getPersonalInformation())
                .build();
    }

    public Instructor convertInstructorDTOToEntity(InstructorDTO instructorDTO) {
        var instructor = new Instructor();
        instructor.setId(instructor.getId());
        instructor.setInstructorId(instructorDTO.getInstructorId());
        instructor.setRole(instructorDTO.getRole());
        instructor.setPersonalInformation(instructorDTO.getPersonalInformation());
        return instructor;
    }
}
