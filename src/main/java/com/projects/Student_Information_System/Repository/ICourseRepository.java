package com.projects.Student_Information_System.Repository;

import com.projects.Student_Information_System.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICourseRepository extends JpaRepository<Course,Long> {
    Optional<Course> findByCode(String code);
    Optional<Course> findByName(String name);


}
