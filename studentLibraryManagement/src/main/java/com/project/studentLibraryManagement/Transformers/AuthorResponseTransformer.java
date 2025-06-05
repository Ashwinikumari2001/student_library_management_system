package com.project.studentLibraryManagement.Transformers;

import com.project.studentLibraryManagement.Models.Address;
import com.project.studentLibraryManagement.Models.Author;
import com.project.studentLibraryManagement.RequestDto.AuthorRequestDto;
import com.project.studentLibraryManagement.ResponseDto.AuthorDeleteDto;
import com.project.studentLibraryManagement.ResponseDto.AuthorResponseDto;
import org.springframework.http.HttpStatus;

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
    public static AuthorResponseDto createAuthorResponseFromRequestDto(Author author, Address address) {
        return AuthorResponseDto.builder()
                .name(author.getName())
                .rating(author.getRating())
                .gender(author.getGender())
                .country(address.getCountry())
                .area(address.getArea())
                .city(address.getCity())
                .state(address.getState())
                .locality(address.getLocality())
                .pincode(address.getPincode())
                .build();
    }
    public static AuthorDeleteDto createAuthorDeleteResponse(Author author){
        return AuthorDeleteDto.builder()
                .authorName(author.getName())
                .httpStatus(HttpStatus.OK)
                .isSuccess(true)
                .build();
    }

}
