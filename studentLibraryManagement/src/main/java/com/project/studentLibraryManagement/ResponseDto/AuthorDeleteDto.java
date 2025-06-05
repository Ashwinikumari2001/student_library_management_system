package com.project.studentLibraryManagement.ResponseDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
public class AuthorDeleteDto {
    private String authorName;
    HttpStatus httpStatus;
    private boolean isSuccess;
}
