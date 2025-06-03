package com.project.studentLibraryManagement.Transformers;

import com.project.studentLibraryManagement.Models.Author;
import com.project.studentLibraryManagement.RequestDto.AuthorRequestDto;

public class AuthorTransformer {
    public static Author createAuthorFromRequestDto(AuthorRequestDto authorRequestDto){
        return Author.builder()
                .name(authorRequestDto.getName())
                .rating(authorRequestDto.getRating())
                .gender(authorRequestDto.getGender())
                .email(authorRequestDto.getEmail())
                .build();
    }
}
