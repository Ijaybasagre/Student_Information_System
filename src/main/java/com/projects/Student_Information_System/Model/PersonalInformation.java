package com.projects.Student_Information_System.Model;

import com.projects.Student_Information_System.Model.Address;
import com.projects.Student_Information_System.Model.Enums.Sex;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonalInformation {

    private String firstname;
    private String lastname;
    private String middlename;

    @Enumerated(EnumType.STRING)
    private Sex sex;
    private LocalDate dateOfBirth;

    @Embedded
    private Address address;
}
