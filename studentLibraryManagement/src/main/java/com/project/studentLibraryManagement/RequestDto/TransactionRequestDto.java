package com.project.studentLibraryManagement.RequestDto;

import com.project.studentLibraryManagement.Enums.TransactionType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class TransactionRequestDto {
    private int fine;
    private Date dueDate;
    private TransactionType transactionType;
    private int card_card_id;
    private int book_book_id;
}
