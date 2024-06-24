package com.projects.Student_Information_System.Model.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.projects.Student_Information_System.Model.Enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
