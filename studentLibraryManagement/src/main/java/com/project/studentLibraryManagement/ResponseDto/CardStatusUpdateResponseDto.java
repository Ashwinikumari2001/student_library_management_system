package com.project.studentLibraryManagement.ResponseDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Builder
@Getter
@Setter
public class CardStatusUpdateResponseDto {
    private int cardUpdatedCount;
    private List<CardResponseDto> cardResponseDtoList;
    private HttpStatus httpStatus;
    private boolean isSuccess;
}
