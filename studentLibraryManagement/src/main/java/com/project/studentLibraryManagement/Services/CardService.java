package com.project.studentLibraryManagement.Services;

import com.project.studentLibraryManagement.Enums.CardStatus;
import com.project.studentLibraryManagement.Models.Author;
import com.project.studentLibraryManagement.Models.Book;
import com.project.studentLibraryManagement.Models.Card;
import com.project.studentLibraryManagement.Models.Student;
import com.project.studentLibraryManagement.Repository.CardRepository;
import com.project.studentLibraryManagement.Repository.StudentRepository;
import com.project.studentLibraryManagement.RequestDto.CardRequestDto;
import com.project.studentLibraryManagement.ResponseDto.BookResponseDto;
import com.project.studentLibraryManagement.ResponseDto.DeleteResponse;
import com.project.studentLibraryManagement.ResponseDto.CardResponseDto;
import com.project.studentLibraryManagement.ResponseDto.CardStatusUpdateResponseDto;
import com.project.studentLibraryManagement.Transformers.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class CardService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CardRepository cardRepository;

    public CardResponseDto createCard(CardRequestDto cardRequestDto,
                                                      int studentId) {
        Student student=studentRepository.findById(studentId).orElseThrow();
        Card card= CardTransformer.createCardFromRequestDto(cardRequestDto);
        student.setCard(card);
        card.setStudent(student);
        Student savedCard=studentRepository.save(student);
        Card card2=savedCard.getCard();
        return CardResponseTransformer.createCardResponseFromCard(student,card2);
    }

    public CardResponseDto updateCard(CardRequestDto cardRequestDto, String studentEmail) {
        Student student=studentRepository.findByEmail(studentEmail).orElseThrow();
        Card card=student.getCard();
        if(card==null){
            throw new RuntimeException("Card doesn't exist for Student Email: "+studentEmail);
        }
        if(card.getCardStatus()== CardStatus.ACTIVE){
            throw new RuntimeException("Card is already ACTIVE");
        }
        if(card.getCardStatus()==CardStatus.BLOCKED && card.getBooks().size()>=5){
            throw new RuntimeException("Please return one or more Books");
        }
        card.setCardStatus(cardRequestDto.getCardStatus());
        card.setExpiryDate(cardRequestDto.getExpiryDate());
        card.setUpdatedDate(new Date());
        student.setCard(card);
        studentRepository.save(student);
        return CardResponseTransformer.createCardResponseFromCard(student,card);
    }

    public CardStatusUpdateResponseDto cardStatusUpdate() {
        List<CardResponseDto> cardResponseDtoList=new ArrayList<>();
        int updatedCardCount=0;

        List<Card> cardList=cardRepository.findByCardStatus(CardStatus.ACTIVE);
        Date date=new Date();
        for(Card card:cardList){
            if(card.getCardStatus()!=CardStatus.BLOCKED) {

                LocalDate createdDate = card.getCreatedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate currentDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                long daysBetween = ChronoUnit.DAYS.between(createdDate, currentDate);

                if (daysBetween > 90 && card.getTransactions().isEmpty()) {
                    updatedCardCount++;
                    card.setCardStatus(CardStatus.INACTIVE);
                    card.setUpdatedDate(new Date());
                    Student student = card.getStudent();
                    cardRepository.save(card);
                    cardResponseDtoList.add(CardResponseTransformer.createCardResponseFromCard(student, card));
                }

                if (card.getExpiryDate().before(date)) {
                    updatedCardCount++;
                    card.setCardStatus(CardStatus.EXPIRED);
                    card.setUpdatedDate(new Date());
                    Student student = card.getStudent();
                    cardRepository.save(card);
                    cardResponseDtoList.add(CardResponseTransformer.createCardResponseFromCard(student, card));
                }
            }
        }
        return CardStatusUpdateTransformer.cardStatusUpdateResponseDto(updatedCardCount,cardResponseDtoList);
    }

    public DeleteResponse cardDelete(int cardId) {
        Card card=cardRepository.findById(cardId).orElseThrow();
        Student student=card.getStudent();
        student.setCard(null);
        List<Book> bookList=card.getBooks();
        for(Book book:bookList){
            book.setCard(null);
            book.setAvailability(true);
        }
        cardRepository.delete(card);
        log.info("Card: {} {} {} {}",card,card.getCardStatus(),card.getCreatedDate(),card.getId());
        return DeleteResponseTransformer.cardDeleteResponse();
    }

    public List<BookResponseDto> getAllBooks(int cardId) {
        Card card=cardRepository.findById(cardId).orElseThrow();
        List<Book> bookList=card.getBooks();
        ArrayList<BookResponseDto> bookResponseDto=new ArrayList<>();
        for(Book book:bookList){
            Author author=book.getAuthor();
            bookResponseDto.add(BookResponseTransformer.createBookResponseDtoFromBook(book,author));
        }
        return bookResponseDto;
    }
}
