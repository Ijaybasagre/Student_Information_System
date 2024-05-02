package com.projects.Student_Information_System.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ContactInformation {

    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String phoneNumber;
}

