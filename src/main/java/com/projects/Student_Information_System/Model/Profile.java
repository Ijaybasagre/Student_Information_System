package com.projects.Student_Information_System.Model;

import com.projects.Student_Information_System.Model.Enums.Role;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
abstract class Profile implements Serializable {

    @Embedded
    private PersonalInformation personalInformation;

    @Enumerated(EnumType.STRING)
    @NotNull(  message = "Role should not be null")
    private Role role;
}
