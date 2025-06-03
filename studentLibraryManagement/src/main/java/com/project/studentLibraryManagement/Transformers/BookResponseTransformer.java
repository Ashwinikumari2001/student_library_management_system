package com.project.studentLibraryManagement.Transformers;

import com.project.studentLibraryManagement.Models.Author;
import com.project.studentLibraryManagement.RequestDto.BookRequestDto;
import com.project.studentLibraryManagement.ResponseDto.BookResponseDto;

public class BookResponseTransformer {
    public static BookResponseDto createBookResponseDtoFromBook(BookRequestDto bookRequestDto, Author author) {
        return BookResponseDto.builder()
                .title(bookRequestDto.getTitle())
                .publisherName(bookRequestDto.getPublisherName())
                .publishedDate(bookRequestDto.getPublishedDate())
                .category(bookRequestDto.getCategory())
                .pages(bookRequestDto.getPages())
                .availability(bookRequestDto.isAvailability())
                .rackNo(bookRequestDto.getRackNo())
                .authorName(author.getName())
                .authorRating(author.getRating())
                .build();
    }
}
