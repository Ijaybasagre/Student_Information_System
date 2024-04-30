package com.projects.Student_Information_System.Repository;

import com.projects.Student_Information_System.Model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IInstructorRepository extends JpaRepository<Instructor,Long> {
}
