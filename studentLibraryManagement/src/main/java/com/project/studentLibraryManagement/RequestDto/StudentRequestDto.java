package com.project.studentLibraryManagement.RequestDto;

import com.project.studentLibraryManagement.Enums.Gender;
import com.project.studentLibraryManagement.Models.Address;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class StudentRequestDto {
    //RequestDto where Dto Stands for Data Transfer Object.
    // it is used to transfer data between different layers of the application.
    //it is used to take input from the user and send it to the service layer.
    //it  is used to create a new student in the application.
    private String name;
    private String email;
    private String mobile;
    private String department;
    private String semester;
    private Gender gender;
    private Date dateOfBirth;
    private String country;
    private String state;
    private String city;
    private String locality;
    private String area;
    private String pincode;
}
