package com.project.studentLibraryManagement.Services;

import com.project.studentLibraryManagement.Enums.CardStatus;
import com.project.studentLibraryManagement.Models.Card;
import com.project.studentLibraryManagement.Models.Student;
import com.project.studentLibraryManagement.Repository.CardRepository;
import com.project.studentLibraryManagement.Repository.StudentRepository;
import com.project.studentLibraryManagement.RequestDto.CardRequestDto;
import com.project.studentLibraryManagement.ResponseDto.CardResponseDto;
import com.project.studentLibraryManagement.ResponseDto.CardStatusUpdateResponseDto;
import com.project.studentLibraryManagement.Transformers.CardResponseTransformer;
import com.project.studentLibraryManagement.Transformers.CardStatusUpdateTransformer;
import com.project.studentLibraryManagement.Transformers.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
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
        studentRepository.saveAndFlush(student);
        return CardResponseTransformer.createCardResponseFromCard(student,card);
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
            if(card.getExpiryDate().before(date)) {
                updatedCardCount++;
                card.setCardStatus(CardStatus.EXPIRED);
                card.setUpdatedDate(new Date());
                Student student=card.getStudent();
                cardRepository.save(card);
                cardResponseDtoList.add(CardResponseTransformer.createCardResponseFromCard(student,card));
            }
            LocalDate createdDate = card.getCreatedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate currentDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            long daysBetween = ChronoUnit.DAYS.between(createdDate, currentDate);

            if(daysBetween>90 && card.getTransactions().isEmpty()){
                updatedCardCount++;
                card.setCardStatus(CardStatus.INACTIVE);
                card.setUpdatedDate(new Date());
                Student student=card.getStudent();
                cardRepository.save(card);
                cardResponseDtoList.add(CardResponseTransformer.createCardResponseFromCard(student,card));
            }

            if(card.getBooks().size()>=5){
                updatedCardCount++;
                card.setCardStatus(CardStatus.BLOCKED);
                card.setUpdatedDate(new Date());
                Student student=card.getStudent();
                cardRepository.save(card);
                cardResponseDtoList.add(CardResponseTransformer.createCardResponseFromCard(student,card));
            }
        }
        return CardStatusUpdateTransformer.cardStatusUpdateResponseDto(updatedCardCount,cardResponseDtoList);
    }
}
