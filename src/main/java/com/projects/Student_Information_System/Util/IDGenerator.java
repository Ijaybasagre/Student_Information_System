package com.projects.Student_Information_System.Util;

import com.projects.Student_Information_System.Model.Enums.Role;
import java.time.LocalDate;
public class IDGenerator {
    public static String generate(Long latestCount, Role role) {
        StringBuilder stringBuilder = new StringBuilder();
        var year = String.valueOf(LocalDate.now().getYear()).substring(2);
        String prefix = role.getCode();
        String suffix = String.format("%04d", latestCount + 1);
        return stringBuilder
                .append(prefix)
                .append(year)
                .append("-")
                .append(suffix)
                .toString();
    }
}
