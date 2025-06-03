package com.project.studentLibraryManagement.Transformers;

import com.project.studentLibraryManagement.Models.Student;
import com.project.studentLibraryManagement.RequestDto.StudentRequestDto;

public class StudentTransformer {
    public static Student createStudentFromRequestDto(StudentRequestDto studentRequestDto){
        return Student.builder()
                .email(studentRequestDto.getEmail())
                .name(studentRequestDto.getName())
                .mobile(studentRequestDto.getMobile())
                .gender(studentRequestDto.getGender())
                .department(studentRequestDto.getDepartment())
                .semester(studentRequestDto.getSemester())
                .dateOfBirth(studentRequestDto.getDateOfBirth())
                .build();
    }
}
