package com.project.studentLibraryManagement.Controllers;

import com.project.studentLibraryManagement.RequestDto.AuthorRequestDto;
import com.project.studentLibraryManagement.ResponseDto.AuthorDeleteDto;
import com.project.studentLibraryManagement.ResponseDto.AuthorResponseDto;
import com.project.studentLibraryManagement.ResponseDto.BookResponseDto;
import com.project.studentLibraryManagement.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;
    @PostMapping("/addAuthor")
    public ResponseEntity<AuthorResponseDto> addAuthor(@RequestBody AuthorRequestDto authorRequestDto){
        AuthorResponseDto authorResponseDto = authorService.addAuthor(authorRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(authorResponseDto);
    }
   @GetMapping("/getAuthorById/{authorId}")
    public ResponseEntity<AuthorResponseDto> getAuthorById(@PathVariable int authorId){
        AuthorResponseDto authorResponseDto=authorService.getAuthorById(authorId);
        return ResponseEntity.status(HttpStatus.OK).body(authorResponseDto);
   }
   @GetMapping("/getAllAuthor")
    public ResponseEntity<List<AuthorResponseDto>> getAllAuthor(){
        List<AuthorResponseDto> authorResponseDtos=authorService.getAllAuthor();
        return ResponseEntity.status(HttpStatus.OK).body(authorResponseDtos);
   }
   @PutMapping("/updateAuthor/{authorId}")
    public ResponseEntity<AuthorResponseDto> updateAuthor(@RequestBody AuthorRequestDto authorRequestDto,@PathVariable int authorId){
        AuthorResponseDto authorResponseDto=authorService.updateAuthor(authorRequestDto,authorId);
        return ResponseEntity.status(HttpStatus.OK).body(authorResponseDto);
   }
   @DeleteMapping("/deleteAuthor/{authorId}")
    public ResponseEntity<AuthorDeleteDto> deleteAuthor(@PathVariable int authorId){
        AuthorDeleteDto authorDeleteDto=authorService.deleteAuthor(authorId);
        return ResponseEntity.status(HttpStatus.OK).body(authorDeleteDto);
   }

   @GetMapping("/getAllBooks/{authorId}")
    public ResponseEntity<List<BookResponseDto>> getAllBookByAuthorId(@PathVariable int authorId){
        List<BookResponseDto> bookResponseDtoList=authorService.getAllBookByAuthorId(authorId);
        return ResponseEntity.status(HttpStatus.OK).body(bookResponseDtoList);
   }
}
