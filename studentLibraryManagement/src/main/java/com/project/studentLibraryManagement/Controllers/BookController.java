package com.project.studentLibraryManagement.Controllers;

import com.project.studentLibraryManagement.RequestDto.BookRequestDto;
import com.project.studentLibraryManagement.ResponseDto.BookResponseDto;
import com.project.studentLibraryManagement.ResponseDto.DeleteResponse;
import com.project.studentLibraryManagement.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/getBookById/{bookId}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable int bookId){
        BookResponseDto bookResponseDto=bookService.getBookById(bookId);
        return ResponseEntity.status(HttpStatus.OK).body(bookResponseDto);
    }
    @GetMapping("/getAllBooks")
    public ResponseEntity<List<BookResponseDto>> getAllBooks(){
        List<BookResponseDto> bookResponseDto=bookService.getAllBooks();
        return ResponseEntity.status(HttpStatus.OK).body(bookResponseDto);
    }
    @PutMapping("/updateBook/{bookId}")
    public ResponseEntity<BookResponseDto> updateBook(@RequestBody BookRequestDto bookRequestDto,
                                                      @PathVariable int bookId){
        BookResponseDto bookResponseDto=bookService.updateBooks(bookRequestDto,bookId);
        return ResponseEntity.status(HttpStatus.OK).body(bookResponseDto);
    }

    @DeleteMapping("/deleteBook/{bookId}")
    public ResponseEntity<DeleteResponse> deleteBook(@PathVariable int bookId){
        DeleteResponse deleteResponse=bookService.deleteBooks(bookId);
        return ResponseEntity.status(HttpStatus.OK).body(deleteResponse);
    }
    @GetMapping("/getAvailableBooks")
    public ResponseEntity<List<BookResponseDto>> getAvailableBooks(){
        List<BookResponseDto> bookResponseDto=bookService.getAvailableBooks();
        return ResponseEntity.status(HttpStatus.OK).body(bookResponseDto);
    }

}
