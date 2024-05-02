package com.projects.Student_Information_System.Model;

import com.projects.Student_Information_System.Model.Address;
import com.projects.Student_Information_System.Model.Enums.Sex;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "first name should not be null")
    private String firstname;
    @NotNull(message = "last name should not be null")
    private String lastname;
    @NotNull(message = "middle name should not be null")
    private String middlename;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @NotNull(message = "date of birth should not be null")
    private LocalDate dateOfBirth;

    @Embedded
    @NotNull(message = "address of birth should not be null")
    private Address address;
}
