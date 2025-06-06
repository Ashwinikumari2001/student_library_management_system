package com.project.studentLibraryManagement.Services;

import com.project.studentLibraryManagement.Enums.CardStatus;
import com.project.studentLibraryManagement.Enums.TransactionType;
import com.project.studentLibraryManagement.Models.Book;
import com.project.studentLibraryManagement.Models.Card;
import com.project.studentLibraryManagement.Models.Student;
import com.project.studentLibraryManagement.Models.Transaction;
import com.project.studentLibraryManagement.Repository.BookRepository;
import com.project.studentLibraryManagement.Repository.CardRepository;
import com.project.studentLibraryManagement.Repository.TransactionRepository;
import com.project.studentLibraryManagement.ResponseDto.TransactionResponseDto;
import com.project.studentLibraryManagement.Transformers.CardResponseTransformer;
import com.project.studentLibraryManagement.Transformers.TransactionTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private BookRepository bookRepository;

    public TransactionResponseDto issueBook(int cardId, int bookId) {
        Card card=cardRepository.findById(cardId).orElseThrow(()->new RuntimeException("Card Not Found with CardId: "+cardId));
        Book book=bookRepository.findById(bookId).orElseThrow(()->new RuntimeException("Book Not Found with BookId: "+bookId));
        if(card.getCardStatus() == CardStatus.ACTIVE){
            if(book.isAvailability()){

                book.setAvailability(false);

                Transaction transaction=TransactionTransformer.createTransaction(book,card);

                book.getTransactions().add(transaction);
                card.getTransactions().add(transaction);
                book.setCard(card);
                card.getBooks().add(book);
                if(card.getBooks().size()>=5){
                    card.setCardStatus(CardStatus.BLOCKED);
                    card.setUpdatedDate(new Date());
                }
                bookRepository.save(book);

                return TransactionTransformer.createTransactionResponse(transaction);
            }else{
                throw new RuntimeException("Book is not available with Id:"+book.getId()+" and Title: "+book.getTitle());
            }
        }else{
            throw new RuntimeException("Card is not ACTIVE, the current status of card is "+card.getCardStatus());
        }
    }

    public TransactionResponseDto returnBook(int cardId, int bookId) {
        Card card=cardRepository.findById(cardId).orElseThrow(()->new RuntimeException("Card Not Found with CardId: "+cardId));
        Book book=bookRepository.findById(bookId).orElseThrow(()->new RuntimeException("Book Not Found with BookId: "+bookId));

        List<Transaction> transactionList=new ArrayList<>();
        if(card.getTransactions().size()<=book.getTransactions().size()){
            transactionList=card.getTransactions();
        }else{
            transactionList=book.getTransactions();
        }

        Transaction transaction=TransactionTransformer.returnTransaction(book,card,transactionList);
        if(transaction==null){
            throw new RuntimeException("Borrow Transaction not exist with BookId: "+bookId+" and CardId: "+cardId);
        }

        book.setAvailability(true);
        book.setCard(null);
        card.getBooks().removeIf(book1 -> book1.getId() == bookId);

        Date date=new Date();

        if(card.getCardStatus()==CardStatus.BLOCKED){
            if(card.getBooks().size()<5){
                card.setCardStatus(CardStatus.ACTIVE);
            }
            LocalDate createdDate = card.getCreatedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate currentDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            long daysBetween = ChronoUnit.DAYS.between(createdDate, currentDate);
            if (daysBetween > 90 && card.getTransactions().isEmpty()) {
                card.setCardStatus(CardStatus.INACTIVE);
            }
            if(card.getExpiryDate().before(date)){
                card.setCardStatus(CardStatus.EXPIRED);
            }

            card.setUpdatedDate(new Date());
        }

        transactionRepository.save(transaction);
        return TransactionTransformer.createTransactionResponse(transaction);
    }
}
