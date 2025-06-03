package com.project.studentLibraryManagement.ResponseDto;

import com.project.studentLibraryManagement.Enums.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthorResponseDto {
    private String name;
    private double rating;
    private Gender gender;
    private String country;
    private String state;
    private String city;
    private String locality;
    private String area;
    private String pincode;
}
