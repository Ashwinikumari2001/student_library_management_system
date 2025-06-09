package com.project.studentLibraryManagement.Models;

import com.project.studentLibraryManagement.Enums.Category;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Book_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id")
    private int id;
    @Column(name = "book_title",nullable = false)
    private String title;
    @Column(name = "publisher_name",nullable = false)
    private String publisherName;
    @Column(name = "published_date",nullable = false)
    private Date publishedDate;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Column(name = "pages",nullable = false)
    private int pages;
    @Column(name = "availability",nullable = false)
    private boolean availability;
    @Column(name = "rack_no",nullable = false)
    private int rackNo;

    @ManyToOne
    private Author author;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    @ManyToOne
    private Card card;
}
