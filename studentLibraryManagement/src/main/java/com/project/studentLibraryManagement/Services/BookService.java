package com.project.studentLibraryManagement.Services;

import com.project.studentLibraryManagement.Models.Author;
import com.project.studentLibraryManagement.Models.Book;
import com.project.studentLibraryManagement.Repository.AuthorRepository;
import com.project.studentLibraryManagement.Repository.BookRepository;
import com.project.studentLibraryManagement.RequestDto.BookRequestDto;
import com.project.studentLibraryManagement.ResponseDto.BookResponseDto;
import com.project.studentLibraryManagement.Transformers.BookResponseTransformer;
import com.project.studentLibraryManagement.Transformers.BookTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    public BookResponseDto addBook(BookRequestDto bookRequestDto, int authorId) {
        // Logic to convert BookRequestDto to Book entity and save it
        // Then convert the saved Book entity to BookResponseDto and return it
        // This is a placeholder implementation
        Book book= BookTransformer.createBookFromRequestDto(bookRequestDto);
        Author author=authorRepository.findById(authorId).orElseThrow();
        book.setAuthor(author);
        author.getBook().add(book);
        bookRepository.save(book);
        return BookResponseTransformer.createBookResponseDtoFromBook(bookRequestDto,author);
    }
}
