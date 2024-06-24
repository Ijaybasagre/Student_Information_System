package com.projects.Student_Information_System.Service;

import com.projects.Student_Information_System.Model.Course;
import com.projects.Student_Information_System.Model.DTO.CourseDTO;
import com.projects.Student_Information_System.Repository.ICourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CourseService {

    private final ICourseRepository courseRepository;


    @Autowired
    public CourseService(ICourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<CourseDTO> getAllCourses(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return courseRepository.findAll(pageable)
                .stream()
                .map(this::convertCourseEntityToDTO)
                .toList();
    }

    public CourseDTO getCourse(Long id) {
        return convertCourseEntityToDTO(findCourseById(id));
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

    CourseDTO convertCourseEntityToDTO(Course course) {
        return CourseDTO.builder()
                .id(course.getId())
                .code(course.getCode())
                .name(course.getName())
                .description(course.getDescription())
                .capacity(course.getCapacity())
                .status(course.getStatus())
                .build();
    }

    Course convertCourseDTOToEntity(CourseDTO courseDTO) {
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
