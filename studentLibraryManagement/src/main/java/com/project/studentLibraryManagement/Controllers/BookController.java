package com.project.studentLibraryManagement.Controllers;

import com.project.studentLibraryManagement.RequestDto.BookRequestDto;
import com.project.studentLibraryManagement.ResponseDto.BookResponseDto;
import com.project.studentLibraryManagement.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @PostMapping("/addBook/{authorId}")
    public ResponseEntity<BookResponseDto> addBook(@RequestBody BookRequestDto bookRequestDto,
                                                   @PathVariable int authorId) {
        BookResponseDto bookResponseDto = bookService.addBook(bookRequestDto,authorId);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookResponseDto);
    }
}
