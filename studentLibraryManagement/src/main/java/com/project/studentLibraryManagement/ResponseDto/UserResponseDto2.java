package com.project.studentLibraryManagement.ResponseDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserResponseDto2 {

    private int userId;
    private String name;
    private String email;
    private String password;
}
