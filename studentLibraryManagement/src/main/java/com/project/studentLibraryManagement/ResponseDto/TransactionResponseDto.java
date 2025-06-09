package com.project.studentLibraryManagement.ResponseDto;

import com.project.studentLibraryManagement.Enums.Category;
import com.project.studentLibraryManagement.Enums.TransactionType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@Builder

public class TransactionResponseDto {
    private int fine;
    private Date transactionDate;
    private Date dueDate;
    private TransactionType transactionType;
    private int cardId;
    private int bookId;
    private String title;
    private int rackNo;
    private Category category;
}
