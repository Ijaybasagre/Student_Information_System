package com.projects.Student_Information_System.Repository;

import com.projects.Student_Information_System.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICourseRepository extends JpaRepository<Course,Long> {
}
