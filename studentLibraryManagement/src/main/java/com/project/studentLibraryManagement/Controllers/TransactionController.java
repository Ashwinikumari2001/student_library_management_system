package com.project.studentLibraryManagement.Controllers;

import com.project.studentLibraryManagement.ResponseDto.TransactionResponseDto;
import com.project.studentLibraryManagement.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/issueBookByStudentCard")
    public ResponseEntity<TransactionResponseDto> issueBook(@RequestParam int cardId,
                                                            @RequestParam int bookId){
        TransactionResponseDto transactionResponseDto=transactionService.issueBook(cardId,bookId);
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionResponseDto);
    }


    @PostMapping("/returnBookByStudentCard")
    public ResponseEntity<TransactionResponseDto> returnBook(@RequestParam int cardId,
                                                             @RequestParam int bookId){
        TransactionResponseDto transactionResponseDto=transactionService.returnBook(cardId,bookId);
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionResponseDto);
    }
    @GetMapping("/getAllTransactions")
    public ResponseEntity<List<TransactionResponseDto>> getAllTransactions(){
        List<TransactionResponseDto> transactionResponseDtoList=transactionService.getAllTransactions();
        return ResponseEntity.status(HttpStatus.OK).body(transactionResponseDtoList);
    }

}
