package com.projects.Student_Information_System.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    private Object data;
    private String message;

    public Response() {
    }

    public Response(Object data, String message) {
        this.data = data;
        this.message = message;
    }
}
