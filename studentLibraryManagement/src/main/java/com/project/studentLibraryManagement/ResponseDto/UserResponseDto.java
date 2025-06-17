package com.project.studentLibraryManagement.ResponseDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
public class UserResponseDto {
    private String name;
    private boolean isSuccess;
}
