package com.project.studentLibraryManagement.Services;

import com.project.studentLibraryManagement.Models.Author;
import com.project.studentLibraryManagement.Models.Book;
import com.project.studentLibraryManagement.Models.Card;
import com.project.studentLibraryManagement.Models.Transaction;
import com.project.studentLibraryManagement.Repository.AuthorRepository;
import com.project.studentLibraryManagement.Repository.BookRepository;
import com.project.studentLibraryManagement.Repository.CardRepository;
import com.project.studentLibraryManagement.RequestDto.BookRequestDto;
import com.project.studentLibraryManagement.ResponseDto.BookResponseDto;
import com.project.studentLibraryManagement.ResponseDto.DeleteResponse;
import com.project.studentLibraryManagement.Transformers.BookResponseTransformer;
import com.project.studentLibraryManagement.Transformers.BookTransformer;
import com.project.studentLibraryManagement.Transformers.DeleteResponseTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CardRepository cardRepository;

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

    public BookResponseDto getBookById(int bookId) {
        Book book=bookRepository.findById(bookId).orElseThrow();
        Author author=book.getAuthor();
        return BookResponseTransformer.createBookResponseDtoFromBook(book,author);
    }

    public List<BookResponseDto> getAllBooks() {
        List<Book> bookList=bookRepository.findAll();
        List<BookResponseDto> bookResponseDto=new ArrayList<>();
        for(Book book:bookList){
            Author author=book.getAuthor();
            bookResponseDto.add(BookResponseTransformer.createBookResponseDtoFromBook(book,author));
        }
        return bookResponseDto;
    }

    public BookResponseDto updateBooks(BookRequestDto bookRequestDto,int bookId) {
        Book book=bookRepository.findById(bookId).orElseThrow(()->new RuntimeException("Book Not Exist with BookId: "+bookId));
        Author author=book.getAuthor();
        book.setPages(bookRequestDto.getPages());
        book.setAvailability(bookRequestDto.isAvailability());
        book.setTitle(bookRequestDto.getTitle());
        book.setCategory(bookRequestDto.getCategory());
        book.setRackNo(bookRequestDto.getRackNo());
        book.setPublisherName(bookRequestDto.getPublisherName());
        book.setPublishedDate(bookRequestDto.getPublishedDate());
        bookRepository.save(book);
        return BookResponseTransformer.createBookResponseDtoFromBook(bookRequestDto,author);
    }

    public DeleteResponse deleteBooks(int bookId) {
        Book book=bookRepository.findById(bookId).orElseThrow(()->new RuntimeException("Book Not Exist with BookId: "+bookId));
        Author author = book.getAuthor();
        author.getBook().removeIf(book1 -> book1.getId() == book.getId());
        authorRepository.save(author);
        Card card = book.getCard();
        if(card!=null){
            card.getBooks().removeIf(book2 -> book2.getId() == bookId);
            cardRepository.save(card);
        }
        bookRepository.delete(book);
        return DeleteResponseTransformer.cardDeleteResponse();
    }

    public List<BookResponseDto> getAvailableBooks() {
        List<Book> available=bookRepository.findByAvailabilityTrue();
        if(available.isEmpty()){
            throw new RuntimeException("Currently all books are not available");
        }else{
            List<BookResponseDto> bookResponseDto=new ArrayList<>();
            for(Book book:available){
                Author author=book.getAuthor();
                bookResponseDto.add(BookResponseTransformer.createBookResponseDtoFromBook(book,author));
            }
            return bookResponseDto;
        }
    }
}
