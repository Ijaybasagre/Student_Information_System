package com.projects.Student_Information_System.Service;

import com.projects.Student_Information_System.Model.DTO.GradeDTO;
import com.projects.Student_Information_System.Model.DTO.GradeSummaryDTO;
import com.projects.Student_Information_System.Model.DTO.StudentDTO;
import com.projects.Student_Information_System.Model.DTO.StudentStatusReportDTO;
import com.projects.Student_Information_System.Model.Enums.Remarks;
import com.projects.Student_Information_System.Model.Grade;
import com.projects.Student_Information_System.Model.StudentCourseId;
import com.projects.Student_Information_System.Repository.IGradeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class GradeService {

    private final IGradeRepository gradeRepository;

    public GradeService(IGradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @Transactional
    public void addGrade(Long studentId, Long courseId, Double value) {
        var grade = new Grade(new StudentCourseId(studentId, courseId), value);
        gradeRepository.save(grade);
    }

    @Transactional
    public void updateGrade(Long studentId, Long courseId, Double value) {
        var id = new StudentCourseId(studentId, courseId);
        var gradeToUpdate = gradeRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Data")
        );
        gradeToUpdate.setValue(value);
        gradeRepository.save(gradeToUpdate);
    }

    public List<GradeDTO> getStudentGrades(Long studentId) {
        return gradeRepository.getStudentGrades(studentId);
    }

    public GradeSummaryDTO getStudentRating(Long studentId) {
        var studentGrades = gradeRepository.getStudentGrades(studentId);
        var average = studentGrades
                .stream()
                .mapToDouble(GradeDTO::getValue)
                .average().orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Data")
                );

        if (average < 75)
            return new GradeSummaryDTO(studentGrades, average, Remarks.FAILED);
        else
            return new GradeSummaryDTO(studentGrades, average, Remarks.PASSED);
    }

    public StudentStatusReportDTO getStudentStatusReport(StudentDTO studentDTO, Long studentId) {
        var studentRating = getStudentRating(studentId);
        return new StudentStatusReportDTO(studentDTO, studentRating);
    }
}
