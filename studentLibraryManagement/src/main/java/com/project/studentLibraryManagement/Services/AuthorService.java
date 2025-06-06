package com.project.studentLibraryManagement.Services;

import com.project.studentLibraryManagement.Models.Address;
import com.project.studentLibraryManagement.Models.Author;
import com.project.studentLibraryManagement.Models.Book;
import com.project.studentLibraryManagement.Repository.AuthorRepository;
import com.project.studentLibraryManagement.RequestDto.AuthorRequestDto;
import com.project.studentLibraryManagement.ResponseDto.AuthorDeleteDto;
import com.project.studentLibraryManagement.ResponseDto.AuthorResponseDto;
import com.project.studentLibraryManagement.ResponseDto.BookResponseDto;
import com.project.studentLibraryManagement.Transformers.AddressTransformer;
import com.project.studentLibraryManagement.Transformers.AuthorResponseTransformer;
import com.project.studentLibraryManagement.Transformers.AuthorTransformer;
import com.project.studentLibraryManagement.Transformers.BookResponseTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public List<AuthorResponseDto> getAllAuthor() {
        List<Author> authorList=authorRepository.findAll();
        List<AuthorResponseDto> authorResponse=new ArrayList<>();
        for(Author author:authorList){
            Address address=author.getAddress();
            authorResponse.add(AuthorResponseTransformer.createAuthorResponseFromRequestDto(author,address));
        }
        return authorResponse;
    }

    public AuthorResponseDto addAuthor(AuthorRequestDto authorRequestDto){
        Author author= AuthorTransformer.createAuthorFromRequestDto(authorRequestDto);
        Address address= AddressTransformer.createAddressFromRequestDto(authorRequestDto);
        author.setAddress(address);
        address.setAuthor(author);
        authorRepository.save(author);
        return AuthorResponseTransformer.createAuthorResponseFromRequestDto(authorRequestDto);
    }

    public AuthorResponseDto getAuthorById(int authorId) {
        Author author=authorRepository.findById(authorId).orElseThrow();
        Address address=author.getAddress();
        return AuthorResponseTransformer.createAuthorResponseFromRequestDto(author,address);
    }

    public AuthorResponseDto updateAuthor(AuthorRequestDto authorRequestDto,int authorId) {
        Author author=authorRepository.findById(authorId).orElseThrow();
        Address address=author.getAddress();
        author.setName(authorRequestDto.getName());
        author.setEmail(authorRequestDto.getEmail());
        author.setRating(authorRequestDto.getRating());
        author.setGender(authorRequestDto.getGender());
        address.setCountry(authorRequestDto.getCountry());
        address.setState(authorRequestDto.getState());
        address.setCity(authorRequestDto.getCity());
        address.setLocality(authorRequestDto.getLocality());
        address.setArea(authorRequestDto.getArea());
        address.setPincode(authorRequestDto.getPincode());
        author.setAddress(address);
        authorRepository.save(author);
        return AuthorResponseTransformer.createAuthorResponseFromRequestDto(authorRequestDto);
    }

    public AuthorDeleteDto deleteAuthor(int authorId) {
        Author author=authorRepository.findById(authorId).orElseThrow();
        authorRepository.deleteById(authorId);
        return AuthorResponseTransformer.createAuthorDeleteResponse(author);
    }

    public List<BookResponseDto> getAllBookByAuthorId(int authorId) {
        Author author=authorRepository.findById(authorId).orElseThrow();
        List<Book> bookList=author.getBook();
        List<BookResponseDto> bookResponseDtoList=new ArrayList<>();
        for(Book book:bookList){
            bookResponseDtoList.add(BookResponseTransformer.createBookResponseDtoFromBook(book,author));
        }
        return bookResponseDtoList;
    }
}
