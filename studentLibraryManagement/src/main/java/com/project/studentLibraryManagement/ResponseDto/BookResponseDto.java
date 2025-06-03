package com.project.studentLibraryManagement.ResponseDto;

import com.project.studentLibraryManagement.Enums.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@Builder
public class BookResponseDto {
    private String title;
    private String publisherName;
    private Date publishedDate;
    private Category category;
    private int pages;
    private boolean availability;
    private int rackNo;
    private String authorName;
    private double authorRating;
}
