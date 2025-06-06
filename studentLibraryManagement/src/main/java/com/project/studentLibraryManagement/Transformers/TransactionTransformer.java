package com.project.studentLibraryManagement.Transformers;

import com.project.studentLibraryManagement.Enums.TransactionType;
import com.project.studentLibraryManagement.Models.Book;
import com.project.studentLibraryManagement.Models.Card;
import com.project.studentLibraryManagement.Models.Transaction;
import com.project.studentLibraryManagement.ResponseDto.TransactionResponseDto;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class TransactionTransformer {
    public static Transaction createTransaction(Book book,Card card){
        Date currentDate = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_MONTH, 30);

        Date newDate = calendar.getTime();
        return Transaction.builder()
                .fine(0)
                .transactionDate(new Date())
                .transactionType(TransactionType.BORROW)
                .dueDate(newDate)
                .book(book)
                .card(card)
                .build();
    }
    public static TransactionResponseDto createTransactionResponse(Transaction transaction){

        return TransactionResponseDto.builder()
                .transactionDate(transaction.getTransactionDate())
                .transactionType(transaction.getTransactionType())
                .dueDate(transaction.getDueDate())
                .fine(transaction.getFine())
                .cardId(transaction.getCard().getId())
                .bookId(transaction.getBook().getId())
                .title(transaction.getBook().getTitle())
                .category(transaction.getBook().getCategory())
                .rackNo(transaction.getBook().getRackNo())
                .build();
    }

    public static Transaction returnTransaction(Book book, Card card, List<Transaction> transactionList) {
        Date dueDate=null;
        for(int i=transactionList.size()-1;i>=0;i--){
            if(transactionList.get(i).getTransactionType()== TransactionType.BORROW && transactionList.get(i).getBook().getId()== book.getId() && Objects.equals(transactionList.get(i).getCard().getId(), card.getId())){
                dueDate=transactionList.get(i).getDueDate();
                break;
            }
        }
        if(dueDate==null){
            return null;
        }
        Date date=new Date();
        LocalDate createdDate = dueDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate currentDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long daysBetween = ChronoUnit.DAYS.between(createdDate, currentDate);
        int totalFine=(int)(daysBetween*5);
        if (totalFine<0){
            totalFine=0;
        }

        return Transaction.builder()
                .fine(totalFine)
                .transactionDate(date)
                .transactionType(TransactionType.RETURN)
                .dueDate(dueDate)
                .book(book)
                .card(card)
                .build();
    }
}
