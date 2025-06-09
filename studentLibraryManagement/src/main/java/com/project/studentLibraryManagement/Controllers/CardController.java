package com.project.studentLibraryManagement.Controllers;

import com.project.studentLibraryManagement.RequestDto.CardRequestDto;
import com.project.studentLibraryManagement.ResponseDto.BookResponseDto;
import com.project.studentLibraryManagement.ResponseDto.DeleteResponse;
import com.project.studentLibraryManagement.ResponseDto.CardResponseDto;
import com.project.studentLibraryManagement.ResponseDto.CardStatusUpdateResponseDto;
import com.project.studentLibraryManagement.Services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    private CardService cardService;

    @PostMapping("/createCard/{studentId}")
    public ResponseEntity<CardResponseDto> createCard(@RequestBody CardRequestDto cardRequestDto,
                                                      @PathVariable int studentId) {
        CardResponseDto cardResponseDto=cardService.createCard(cardRequestDto,studentId);
        return ResponseEntity.status(HttpStatus.CREATED).body(cardResponseDto);
    }
    @PutMapping("/updateCard/{studentEmail}")
    public ResponseEntity<CardResponseDto> updateCard(@RequestBody CardRequestDto cardRequestDto,@PathVariable String studentEmail){
        CardResponseDto cardResponseDto=cardService.updateCard(cardRequestDto,studentEmail);
        return ResponseEntity.status(HttpStatus.OK).body(cardResponseDto);
    }

    @PutMapping("/updateCardStatus")
    public ResponseEntity<CardStatusUpdateResponseDto> cardStatusUpdate(){
        CardStatusUpdateResponseDto cardStatusUpdateResponseDto=cardService.cardStatusUpdate();
        return ResponseEntity.status(HttpStatus.OK).body(cardStatusUpdateResponseDto);
    }
    @DeleteMapping("/deleteCard/{cardId}")
    public ResponseEntity<DeleteResponse> deleteCard(@PathVariable int cardId){
        DeleteResponse deleteResponse =cardService.cardDelete(cardId);
        return ResponseEntity.status(HttpStatus.OK).body(deleteResponse);
    }
   @GetMapping("/getAllBooks/{cardId}")
    public ResponseEntity<List<BookResponseDto>> getAllBooks(@PathVariable int cardId){
        List<BookResponseDto> bookResponseDto=cardService.getAllBooks(cardId);
        return ResponseEntity.status(HttpStatus.OK).body(bookResponseDto);
   }
}
