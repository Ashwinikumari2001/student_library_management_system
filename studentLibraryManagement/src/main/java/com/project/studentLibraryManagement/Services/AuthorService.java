package com.project.studentLibraryManagement.Services;

import com.project.studentLibraryManagement.Models.Address;
import com.project.studentLibraryManagement.Models.Author;
import com.project.studentLibraryManagement.Repository.AuthorRepository;
import com.project.studentLibraryManagement.RequestDto.AuthorRequestDto;
import com.project.studentLibraryManagement.ResponseDto.AuthorResponseDto;
import com.project.studentLibraryManagement.Transformers.AddressTransformer;
import com.project.studentLibraryManagement.Transformers.AuthorResponseTransformer;
import com.project.studentLibraryManagement.Transformers.AuthorTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    public AuthorResponseDto addAuthor(AuthorRequestDto authorRequestDto){
        Author author= AuthorTransformer.createAuthorFromRequestDto(authorRequestDto);
        Address address= AddressTransformer.createAddressFromRequestDto(authorRequestDto);
        author.setAddress(address);
        address.setAuthor(author);
        authorRepository.save(author);
        return AuthorResponseTransformer.createAuthorResponseFromRequestDto(authorRequestDto);
    }
}
