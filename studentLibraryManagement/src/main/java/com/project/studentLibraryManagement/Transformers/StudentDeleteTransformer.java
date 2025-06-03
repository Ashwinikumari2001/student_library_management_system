package com.project.studentLibraryManagement.Transformers;

import com.project.studentLibraryManagement.Models.Student;
import com.project.studentLibraryManagement.ResponseDto.StudentDeleteDto;
import org.springframework.http.HttpStatus;

public class StudentDeleteTransformer {
    public static StudentDeleteDto studentDeleteDto(Student student){
        return StudentDeleteDto.builder()
                .studentName(student.getName())
                .email(student.getEmail())
                .httpStatus(HttpStatus.OK)
                .isSuccess(true)
                .build();
    }
}
