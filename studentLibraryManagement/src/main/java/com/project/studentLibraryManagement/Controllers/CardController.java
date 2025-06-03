package com.project.studentLibraryManagement.Controllers;

import com.project.studentLibraryManagement.RequestDto.CardRequestDto;
import com.project.studentLibraryManagement.ResponseDto.CardResponseDto;
import com.project.studentLibraryManagement.Services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
