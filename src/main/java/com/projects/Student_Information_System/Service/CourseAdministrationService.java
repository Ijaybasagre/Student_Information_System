package com.projects.Student_Information_System.Service;

import com.projects.Student_Information_System.Model.DTO.GradeDTO;
import com.projects.Student_Information_System.Model.DTO.GradeSummaryDTO;
import com.projects.Student_Information_System.Model.DTO.InstructorDTO;
import com.projects.Student_Information_System.Model.DTO.StudentDTO;
import com.projects.Student_Information_System.Model.DTO.StudentStatusReportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CourseAdministrationService {

    private final CourseService courseService;
    private final StudentService studentService;
    private final InstructorService instructorService;

    private final GradeService gradeService;
    @Autowired
    public CourseAdministrationService(CourseService courseService, StudentService studentService, InstructorService instructorService, GradeService gradeService) {
        this.courseService = courseService;
        this.studentService = studentService;
        this.instructorService = instructorService;
        this.gradeService = gradeService;
    }

    public List<StudentDTO> getCourseStudent(Long id) {
        return courseService.findCourseById(id).getEnrolledStudents()
                .stream().
                map(studentService::convertStudentEntityToDTO)
                .toList();
    }

    public Set<InstructorDTO> getCourseInstructors(Long id) {
        return courseService.findCourseById(id).getAssignedInstructors()
                .stream()
                .map(instructorService::convertInstructorEntityToDTO)
                .collect(Collectors.toSet());
    }

    @Transactional
    public String addStudent(Long courseId, Long studentId) {
        var course = courseService.findCourseById(courseId);
        var student = studentService.findStudentById(studentId);
        if (course.getEnrolledStudents().size() < course.getCapacity()) {
            course.getEnrolledStudents().add(student);
        } else {
            return "Maximum capacity reached";
        }
        var courseDTO = courseService.convertCourseEntityToDTO(course);
        courseService.updateCourse(courseId, courseDTO);
        return "Student enrolled successfully";
    }

    @Transactional
    public void removeStudent(Long courseId, Long studentId) {
        var course = courseService.findCourseById(courseId);
        var student = studentService.findStudentById(studentId);
        if (!course.getEnrolledStudents().isEmpty()) {
            course.setCapacity(course.getCapacity() - 1);
            course.getEnrolledStudents().remove(student);
            courseService.updateCourse(courseId, courseService.convertCourseEntityToDTO(course));
        }
    }

    @Transactional
    public void addInstructor(Long courseId, Long instructorId) {
        var course = courseService.findCourseById(courseId);
        var instructor = instructorService.findInstructorById(instructorId);
        course.getAssignedInstructors().add(instructor);
        courseService.updateCourse(courseId, courseService.convertCourseEntityToDTO(course));
    }

    @Transactional
    public void removeInstructor(Long courseId, Long instructorId) {
        var course = courseService.findCourseById(courseId);
        var instructor = instructorService.findInstructorById(instructorId);
        course.getAssignedInstructors().remove(instructor);
        courseService.updateCourse(courseId, courseService.convertCourseEntityToDTO(course));
    }

    public void addGrade(Long studentId, Long courseId, Double value) {
        gradeService.addGrade(studentId, courseId, value);
    }

    public void updateGrade(Long studentId, Long courseId, Double value) {
        gradeService.updateGrade(studentId, courseId, value);
    }

    public List<GradeDTO> getStudentGrades(Long studentId) {
        return gradeService.getStudentGrades(studentId);
    }

    public GradeSummaryDTO getStudentRating(Long studentId) {
        return gradeService.getStudentRating(studentId);
    }

  public StudentStatusReportDTO getStudentStatusReport(Long studentId) {
        var studentDTO = studentService.getStudent(studentId);
        return gradeService.getStudentStatusReport(studentDTO, studentId);
    }
}
