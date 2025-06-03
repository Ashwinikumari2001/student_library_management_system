package com.project.studentLibraryManagement.RequestDto;

import com.project.studentLibraryManagement.Enums.Gender;
import com.project.studentLibraryManagement.Models.Address;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorRequestDto {
    private String name;
    private String email;
    private double rating;
    private Gender gender;

    private String country;
    private String state;
    private String city;
    private String locality;
    private String area;
    private String pincode;
}
