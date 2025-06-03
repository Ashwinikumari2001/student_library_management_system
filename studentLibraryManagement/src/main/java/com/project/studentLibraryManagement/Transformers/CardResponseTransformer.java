package com.project.studentLibraryManagement.Transformers;

import com.project.studentLibraryManagement.Models.Card;
import com.project.studentLibraryManagement.Models.Student;
import com.project.studentLibraryManagement.ResponseDto.CardResponseDto;

public class CardResponseTransformer {
    public static CardResponseDto createCardResponseFromCard(Student student, Card card) {
        return CardResponseDto.builder()
                .studentName(student.getName())
                .studentDepartment(student.getDepartment())
                .studentSemester(student.getSemester())
                .cardId(card.getId())
                .cardStatus(card.getCardStatus())
                .expiryDate(card.getExpiryDate())
                .createdDate(card.getCreatedDate())
                .build();
    }
}
