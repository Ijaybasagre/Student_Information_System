package com.projects.Student_Information_System.Model.DTO;

import com.projects.Student_Information_System.Model.Enums.Remarks;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GradeSummaryDTO {

    private List<GradeDTO> grades;

    private Double average;

    private Remarks remarks;

}
