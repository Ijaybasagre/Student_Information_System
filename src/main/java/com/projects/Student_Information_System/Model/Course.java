package com.projects.Student_Information_System.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String code;

    @Column(nullable = false,unique = true)
    private String name;

    private String description;

    private Integer capacity;

    @ManyToMany
    @JoinTable(
            name = "enrolled_students",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns=@JoinColumn(name="student_id")
    )
    private List<Student> enrolledStudents;


}
