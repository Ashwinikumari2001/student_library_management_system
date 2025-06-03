package com.project.studentLibraryManagement.ResponseDto;

import com.project.studentLibraryManagement.Enums.CardStatus;
import com.project.studentLibraryManagement.Enums.Gender;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class StudentResponseDto2 {
    private String name;
    private String emailId;
    private String department;
    private String semester;
    private Gender gender;

    private int cardId;
    private CardStatus cardStatus;
    private Date expiryDate;
    private Date createdDate;

}
