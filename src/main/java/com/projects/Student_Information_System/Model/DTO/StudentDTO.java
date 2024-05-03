package com.projects.Student_Information_System.Model.DTO;

import com.projects.Student_Information_System.Model.ContactInformation;
import com.projects.Student_Information_System.Model.Course;
import com.projects.Student_Information_System.Model.PersonalInformation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDTO {
    private Long id;
    private PersonalInformation personalInformation;
    private ContactInformation contactInformation;
}
