package com.project.studentLibraryManagement.Transformers;

import com.project.studentLibraryManagement.Models.Card;
import com.project.studentLibraryManagement.Models.Student;
import com.project.studentLibraryManagement.ResponseDto.StudentResponseDto2;

public class StudentResponseTransformer2 {
    public static StudentResponseDto2 createResponse(Student student, Card card){
        return StudentResponseDto2.builder()
                .name(student.getName())
                .emailId(student.getEmail())
                .semester(student.getSemester())
                .department(student.getDepartment())
                .gender(student.getGender())
                .cardId(card.getId())
                .createdDate(card.getCreatedDate())
                .expiryDate(card.getExpiryDate())
                .cardStatus(card.getCardStatus())
                .build();
    }
}
