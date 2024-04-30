package com.projects.Student_Information_System.Repository;

import com.projects.Student_Information_System.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStudentRepository extends JpaRepository<Student,Long> {
}
