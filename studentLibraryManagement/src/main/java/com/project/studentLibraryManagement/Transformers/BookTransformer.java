package com.project.studentLibraryManagement.Transformers;

import com.project.studentLibraryManagement.Models.Book;
import com.project.studentLibraryManagement.RequestDto.BookRequestDto;

public class BookTransformer {
    public static Book createBookFromRequestDto(BookRequestDto bookRequestDto){
        return Book.builder()
                .title(bookRequestDto.getTitle())
                .publisherName(bookRequestDto.getPublisherName())
                .publishedDate(bookRequestDto.getPublishedDate())
                .category(bookRequestDto.getCategory())
                .rackNo(bookRequestDto.getRackNo())
                .pages(bookRequestDto.getPages())
                .availability(bookRequestDto.isAvailability())
                .build();
    }
}
