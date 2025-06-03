package com.project.studentLibraryManagement.ResponseDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
public class StudentDeleteDto {
    HttpStatus httpStatus;
    private String studentName;
    private String email;
    private boolean isSuccess;
}
