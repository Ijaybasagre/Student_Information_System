package com.projects.Student_Information_System.Model;

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
public class Address implements Serializable {
    private String street;
    @NotNull(message = "city/town should not be null")
    private String city;
    @NotNull(message = "state/municipality should not be null")
    private String state;
    @NotNull(message = "postalCodey should not be null")
    private String postalCode;
    @NotNull(message = "country should not be null")
    private String country;
}
