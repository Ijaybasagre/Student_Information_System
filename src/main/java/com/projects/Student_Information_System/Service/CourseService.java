package com.projects.Student_Information_System.Service;

import com.projects.Student_Information_System.Model.AppMessage;
import com.projects.Student_Information_System.Model.Course;
import com.projects.Student_Information_System.Model.DTO.CourseDTO;
import com.projects.Student_Information_System.Model.DTO.InstructorDTO;
import com.projects.Student_Information_System.Model.DTO.StudentDTO;
import com.projects.Student_Information_System.Repository.ICourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final ICourseRepository courseRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private InstructorService instructorService;

    @Autowired
    public CourseService(ICourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(this::convertCourseEntityToDTO)
                .collect(Collectors.toList());
    }

    public CourseDTO getCourse(Long id) {
        return convertCourseEntityToDTO(findCourseById(id));
    }

    public Set<StudentDTO> getCourseStudent(Long id) {
        return findCourseById(id).getEnrolledStudents()
                .stream().
                map(studentService::convertStudentEntityToDTO)
                .collect(Collectors.toSet());
    }

    public Set<InstructorDTO> getCourseInstructors(Long id) {
        return findCourseById(id).getAssignedInstructors()
                .stream()
                .map(instructorService::convertInstructorEntityToDTO)
                .collect(Collectors.toSet());
    }

    @Transactional
    public CourseDTO createCourse(CourseDTO courseDTO) {
        var course = convertCourseDTOToEntity(courseDTO);
        return convertCourseEntityToDTO(courseRepository.save(course));
    }

    @Transactional
    public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {
        var courseToBeUpdate = findCourseById(id);
        courseToBeUpdate.setId(courseDTO.getId());
        courseToBeUpdate.setCode(courseDTO.getCode());
        courseToBeUpdate.setName(courseDTO.getName());
        courseToBeUpdate.setDescription(courseDTO.getDescription());
        courseToBeUpdate.setCapacity(courseDTO.getCapacity());
        return convertCourseEntityToDTO(courseRepository.save(courseToBeUpdate));
    }

    @Transactional
    public void deleteCourse(Long id) {
        var courseToBeDeleted = findCourseById(id);
        courseRepository.deleteById(courseToBeDeleted.getId());
    }

    public Course findCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course id: " + id + " Doesn't exist")
                );
    }

    @Transactional
    public AppMessage addStudent(Long courseId, Long studentId) {
        var course = findCourseById(courseId);
        var student = studentService.findStudentById(studentId);
        if (course.getEnrolledStudents().size() < course.getCapacity()) {
            course.getEnrolledStudents().add(student);
        } else {
            return new AppMessage("Maximum capacity reached");
        }
        courseRepository.save(course);
        return new AppMessage("Enrollment successful!");
    }

    @Transactional
    public AppMessage removeStudent(Long courseId, Long studentId) {
        var course = findCourseById(courseId);
        var student = studentService.findStudentById(studentId);
        if (course.getEnrolledStudents().size() > 0) {
            course.setCapacity(course.getCapacity() - 1);
            course.getEnrolledStudents().remove(student);
            courseRepository.save(course);
        }
        return new AppMessage("Updated Successfully");
    }

    @Transactional
    public AppMessage addInstructor(Long courseId, Long instructorId) {
        var course = findCourseById(courseId);
        var instructor = instructorService.findInstructorById(instructorId);
        course.getAssignedInstructors().add(instructor);
        courseRepository.save(course);
        return new AppMessage("Assignment successful!");
    }

    @Transactional
    public AppMessage removeInstructor(Long courseId, Long instructorId) {
        var course = findCourseById(courseId);
        var instructor = instructorService.findInstructorById(instructorId);
        course.getAssignedInstructors().remove(instructor);
        courseRepository.save(course);
        return new AppMessage("Updated Successfully");
    }


    private CourseDTO convertCourseEntityToDTO(Course course) {
        return CourseDTO.builder()
                .id(course.getId())
                .code(course.getCode())
                .name(course.getName())
                .description(course.getDescription())
                .capacity(course.getCapacity())
                .status(course.getStatus())
                .build();
    }

    private Course convertCourseDTOToEntity(CourseDTO courseDTO) {
        return Course.builder()
                .id(courseDTO.getId())
                .code(courseDTO.getCode())
                .name(courseDTO.getName())
                .description(courseDTO.getDescription())
                .capacity(courseDTO.getCapacity())
                .status(courseDTO.getStatus())
                .build();
    }


}
