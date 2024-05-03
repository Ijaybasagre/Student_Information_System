package com.projects.Student_Information_System.Model.DTO;


import com.projects.Student_Information_System.Model.ContactInformation;
import com.projects.Student_Information_System.Model.Enums.Role;
import com.projects.Student_Information_System.Model.PersonalInformation;
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
public class InstructorDTO {
    private Long id;
    private String instructorId;
    private Role role;
    private PersonalInformation personalInformation;

}
