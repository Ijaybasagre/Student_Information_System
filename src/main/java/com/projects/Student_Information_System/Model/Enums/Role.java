package com.projects.Student_Information_System.Model.Enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Getter
public enum Role {
    STUDENT("Student","ST"),
    INSTRUCTOR("Instructor","IN"),
    ADMINISTRATOR("Administrator","AD"),
    STAFF("Staff","SF");

    String name;
    String code;
}
