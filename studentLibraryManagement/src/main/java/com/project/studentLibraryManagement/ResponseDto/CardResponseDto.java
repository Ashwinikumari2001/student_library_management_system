package com.project.studentLibraryManagement.ResponseDto;

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
    private int cardId;
    private String cardStatus;
    private Date expiryDate;
    private Date createdDate;
}
