package com.project.studentLibraryManagement.Transformers;

import com.project.studentLibraryManagement.RequestDto.StudentRequestDto;
import com.project.studentLibraryManagement.ResponseDto.StudentResponseDto;

public class StudentResponseTransformer {
    public static StudentResponseDto createResponseDtoFromStudent(StudentRequestDto studentRequestDto) {
        return StudentResponseDto.builder()
                .name(studentRequestDto.getName())
                .department(studentRequestDto.getDepartment())
                .semester(studentRequestDto.getSemester())
                .gender(studentRequestDto.getGender())
                .country(studentRequestDto.getCountry())
                .state(studentRequestDto.getState())
                .city(studentRequestDto.getCity())
                .locality(studentRequestDto.getLocality())
                .area(studentRequestDto.getArea())
                .pincode(studentRequestDto.getPincode())
                .build();
    }
}
