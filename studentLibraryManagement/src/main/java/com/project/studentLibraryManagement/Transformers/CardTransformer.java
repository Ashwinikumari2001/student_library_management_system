package com.project.studentLibraryManagement.Transformers;

import com.project.studentLibraryManagement.Models.Card;
import com.project.studentLibraryManagement.RequestDto.CardRequestDto;

public class CardTransformer {
    public static Card createCardFromRequestDto(CardRequestDto cardRequestDto) {
        return Card.builder()
                .cardStatus(cardRequestDto.getCardStatus())
                .expiryDate(cardRequestDto.getExpiryDate())
                .build();
    }
}
