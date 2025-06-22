package com.project.studentLibraryManagement.Transformers;

import com.project.studentLibraryManagement.Models.User;
import com.project.studentLibraryManagement.RequestDto.UserRequestDto;
import com.project.studentLibraryManagement.ResponseDto.UserResponseDto;
import com.project.studentLibraryManagement.ResponseDto.UserResponseDto2;
import org.springframework.http.HttpStatus;

public class UserTransformer {
    public static User createUserFromUserRequestDto(UserRequestDto userRequestDto){
        return User.builder()
                .name(userRequestDto.getName())
                .email(userRequestDto.getEmail())
                .password(userRequestDto.getPassword())
                .build();
    }
    public static UserResponseDto createUserResponseDto(User user){
        return UserResponseDto.builder()
                .name(user.getName())
                .isSuccess(true)
                .build();
    }
    public static UserResponseDto2 createUserResponseDto2(User user){
        return UserResponseDto2.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

}
