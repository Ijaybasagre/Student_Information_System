package com.projects.Student_Information_System.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ContactInformation implements Serializable {

    @NotNull(message = "Email should not be null")
    @Column(unique = true)
    private String email;

    @NotNull(message = "phoneNumber should not be null")
    @Column(unique = true)
    private String phoneNumber;
}

