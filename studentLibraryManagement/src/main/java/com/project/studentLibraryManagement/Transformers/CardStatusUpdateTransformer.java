package com.project.studentLibraryManagement.Transformers;

import com.project.studentLibraryManagement.ResponseDto.CardResponseDto;
import com.project.studentLibraryManagement.ResponseDto.CardStatusUpdateResponseDto;
import org.springframework.http.HttpStatus;

import java.util.List;

public class CardStatusUpdateTransformer {
    public static CardStatusUpdateResponseDto cardStatusUpdateResponseDto(int updatedCardCount, List<CardResponseDto> cardResponseDtoList){
        return CardStatusUpdateResponseDto.builder()
                .isSuccess(true)
                .httpStatus(HttpStatus.OK)
                .cardUpdatedCount(updatedCardCount)
                .cardResponseDtoList(cardResponseDtoList)
                .build();
    }
}
