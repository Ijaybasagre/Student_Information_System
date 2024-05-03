package com.projects.Student_Information_System.Service;

import com.projects.Student_Information_System.Model.DTO.GradeDTO;
import com.projects.Student_Information_System.Model.DTO.GradeSummaryDTO;
import com.projects.Student_Information_System.Model.Enums.Remarks;
import com.projects.Student_Information_System.Model.Grade;
import com.projects.Student_Information_System.Model.StudentCourseId;
import com.projects.Student_Information_System.Model.DTO.StudentStatusReportDTO;
import com.projects.Student_Information_System.Repository.IGradeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class GradeService {

    private final IGradeRepository gradeRepository;

    private final CourseService courseService;
    private final StudentService studentService;

    public GradeService(IGradeRepository gradeRepository, CourseService courseService, StudentService studentService) {
        this.gradeRepository = gradeRepository;
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @Transactional
    public void addGrade(Long studentId, Long courseId, Double value) {
        var student = studentService.findStudentById(studentId);
        var course = courseService.findCourseById(courseId);
        var grade = new Grade(new StudentCourseId(student.getId(), course.getId()), value);
        gradeRepository.save(grade);
    }

    @Transactional
    public void updateGrade(Long studentId, Long courseId, Double value) {
        var student = studentService.findStudentById(studentId);
        var course = courseService.findCourseById(courseId);
        var id = new StudentCourseId(student.getId(), course.getId());
        var gradeToUpdate = gradeRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Data")
        );
        gradeToUpdate.setValue(value);
        gradeRepository.save(gradeToUpdate);
    }

    public List<GradeDTO> getStudentGrades(Long studentId) {
        var student = studentService.findStudentById(studentId);
        return gradeRepository.getStudentGrades(student.getId());
    }

    public GradeSummaryDTO getStudentRating(Long studentId) {
        var student = studentService.findStudentById(studentId);
        var studentGrades = gradeRepository.getStudentGrades(student.getId());
        var average = studentGrades
                .stream()
                .mapToDouble(GradeDTO::getValue)
                .average().getAsDouble();

        if (average < 75)
            return new GradeSummaryDTO(studentGrades, average, Remarks.FAILED);
        else
            return new GradeSummaryDTO(studentGrades, average, Remarks.PASSED);
    }

    public StudentStatusReportDTO getStudentStatusReport(Long studentId) {
        var student = studentService.getStudent(studentId);
        var studentRating =getStudentRating(studentId);
        return new StudentStatusReportDTO(student,studentRating);
    }
}
