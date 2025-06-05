package com.project.studentLibraryManagement.ResponseDto;

import com.project.studentLibraryManagement.Enums.CardStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@Getter
@Setter
public class CardResponseDto {
    private String studentName;
    private String studentDepartment;
    private String studentSemester;
    private Integer cardId;
    private CardStatus cardStatus;
    private Date expiryDate;
    private Date createdDate;
}
