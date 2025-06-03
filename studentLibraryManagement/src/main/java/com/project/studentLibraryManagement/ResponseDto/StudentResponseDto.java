package com.project.studentLibraryManagement.ResponseDto;

import com.project.studentLibraryManagement.Enums.Gender;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class StudentResponseDto {
    private String name;
    private String department;
    private String semester;
    private Gender gender;
    private String country;
    private String state;
    private String city;
    private String locality;
    private String area;
    private String pincode;
}
