package com.projects.Student_Information_System.Repository;

import com.projects.Student_Information_System.Model.DTO.GradeDTO;
import com.projects.Student_Information_System.Model.Grade;
import com.projects.Student_Information_System.Model.StudentCourseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IGradeRepository extends JpaRepository<Grade, StudentCourseId> {

    @Transactional(readOnly = true)
    @Query(value = "SELECT crs.code AS code,crs.name AS name, gr.value AS value " +
            "FROM COURSE crs INNER JOIN GRADE gr ON crs.id = gr.course_id " +
            "WHERE gr.student_id = ?1", nativeQuery = true)
   List<GradeDTO> getStudentGrades(Long id);
}
