package com.project.studentLibraryManagement.Transformers;

import com.project.studentLibraryManagement.RequestDto.AuthorRequestDto;
import com.project.studentLibraryManagement.ResponseDto.AuthorResponseDto;

public class AuthorResponseTransformer {
    public static AuthorResponseDto createAuthorResponseFromRequestDto(AuthorRequestDto authorRequestDto) {
        return AuthorResponseDto.builder()
                .name(authorRequestDto.getName())
                .rating(authorRequestDto.getRating())
                .gender(authorRequestDto.getGender())
                .country(authorRequestDto.getCountry())
                .area(authorRequestDto.getArea())
                .city(authorRequestDto.getCity())
                .state(authorRequestDto.getState())
                .locality(authorRequestDto.getLocality())
                .pincode(authorRequestDto.getPincode())
                .build();
    }
}
