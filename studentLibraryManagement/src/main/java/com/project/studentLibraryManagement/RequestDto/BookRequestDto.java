package com.project.studentLibraryManagement.RequestDto;

import com.project.studentLibraryManagement.Enums.Category;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class BookRequestDto {
    private String title;
    private String publisherName;
    private Date publishedDate;
    private Category category;
    private int pages;
    private boolean availability;
    private int rackNo;
    private int author_id;
    private int card_card_id;
}
