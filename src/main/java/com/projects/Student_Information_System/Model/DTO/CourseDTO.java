package com.projects.Student_Information_System.Model.DTO;

import com.projects.Student_Information_System.Model.Enums.Status;
import com.projects.Student_Information_System.Model.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDTO {
    private Long id;
    private String code;
    private String name;
    private String description;
    private Integer capacity;
    private Status status;
}
