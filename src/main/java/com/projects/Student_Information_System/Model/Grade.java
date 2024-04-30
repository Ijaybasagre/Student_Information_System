package com.projects.Student_Information_System.Model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Grade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EmbeddedId
    private StudentSubjectId id;

    private double value;

}
