package com.project.studentLibraryManagement.Services;

import com.project.studentLibraryManagement.Models.Card;
import com.project.studentLibraryManagement.Models.Student;
import com.project.studentLibraryManagement.Repository.StudentRepository;
import com.project.studentLibraryManagement.RequestDto.CardRequestDto;
import com.project.studentLibraryManagement.ResponseDto.CardResponseDto;
import com.project.studentLibraryManagement.Transformers.CardResponseTransformer;
import com.project.studentLibraryManagement.Transformers.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class CardService {
    @Autowired
    private StudentRepository studentRepository;

    public CardResponseDto createCard(CardRequestDto cardRequestDto,
                                                      int studentId) {
        Student student=studentRepository.findById(studentId).orElseThrow();
        Card card= CardTransformer.createCardFromRequestDto(cardRequestDto);
        student.setCard(card);
        card.setStudent(student);
        studentRepository.saveAndFlush(student);
        return CardResponseTransformer.createCardResponseFromCard(student,card);
    }
}
