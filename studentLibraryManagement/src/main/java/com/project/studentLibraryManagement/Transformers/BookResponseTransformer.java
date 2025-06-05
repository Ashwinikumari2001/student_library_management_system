package com.project.studentLibraryManagement.Transformers;

import com.project.studentLibraryManagement.Models.Author;
import com.project.studentLibraryManagement.Models.Book;
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
    public static BookResponseDto createBookResponseDtoFromBook(Book book, Author author) {
        return BookResponseDto.builder()
                .title(book.getTitle())
                .publisherName(book.getPublisherName())
                .publishedDate(book.getPublishedDate())
                .category(book.getCategory())
                .pages(book.getPages())
                .availability(book.isAvailability())
                .rackNo(book.getRackNo())
                .authorName(author.getName())
                .authorRating(author.getRating())
                .build();
    }
}
