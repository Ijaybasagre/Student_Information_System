package com.projects.Student_Information_System.Service;

import com.projects.Student_Information_System.Model.DTO.StudentDTO;
import com.projects.Student_Information_System.Model.Enums.Role;
import com.projects.Student_Information_System.Model.Student;
import com.projects.Student_Information_System.Repository.IStudentRepository;
import com.projects.Student_Information_System.Util.IDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final IStudentRepository studentRepository;

    @Autowired
    public StudentService(IStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this::convertStudentEntityToDTO)
                .collect(Collectors.toList());
    }

    public StudentDTO getStudent(Long id) {
        return convertStudentEntityToDTO(findStudentById(id));
    }

    @Transactional
    public StudentDTO createStudent(StudentDTO studentDTO) {
        var student = convertStudentDTOToEntity(studentDTO);
        student.setStudentId(IDGenerator.generate(getLatestCount(), Role.STUDENT));
        student.setRole(Role.STUDENT);
        return convertStudentEntityToDTO(studentRepository.save(student));
    }

    @Transactional
    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
        var studentToUpdate = findStudentById(id);
        studentToUpdate.setPersonalInformation(studentDTO.getPersonalInformation());
        return convertStudentEntityToDTO(studentRepository.save(studentToUpdate));
    }

    @Transactional
    public void deleteStudent(Long id) {
        var studentToDelete = findStudentById(id);
        studentRepository.deleteById(studentToDelete.getId());
    }

    public Student findStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student id: " + id + " Doesn't exist")
                );
    }

    private Long getLatestCount() {
        return getAllStudents()
                .stream()
                .mapToLong(StudentDTO::getId).max().orElse(0L);
    }

    public StudentDTO convertStudentEntityToDTO(Student student) {
        var studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setStudentId(student.getStudentId());
        studentDTO.setRole(student.getRole());
        studentDTO.setPersonalInformation(student.getPersonalInformation());
        return studentDTO;
    }

    public Student convertStudentDTOToEntity(StudentDTO studentDTO) {
        var student = new Student();
        student.setStudentId(studentDTO.getStudentId());
        student.setRole(studentDTO.getRole());
        student.setPersonalInformation(studentDTO.getPersonalInformation());
        return student;
    }
}
