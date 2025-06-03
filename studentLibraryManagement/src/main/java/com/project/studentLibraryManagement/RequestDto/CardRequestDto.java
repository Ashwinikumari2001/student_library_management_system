package com.project.studentLibraryManagement.RequestDto;

import com.project.studentLibraryManagement.Enums.CardStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class CardRequestDto {
    private CardStatus cardStatus;
    private Date expiryDate;
}
