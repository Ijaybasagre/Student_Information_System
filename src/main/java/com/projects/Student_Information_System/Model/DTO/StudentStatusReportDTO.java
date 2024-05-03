package com.projects.Student_Information_System.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentStatusReportDTO {

    private StudentDTO studentInformation;

    private GradeSummaryDTO rating;
}
