package com.project.studentLibraryManagement.Models;

import com.project.studentLibraryManagement.Enums.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Book_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne(cascade = CascadeType.ALL)
    private Author author;

    @OneToMany(mappedBy = "book")
    private List<Transaction> transactions;
    @ManyToOne(cascade = CascadeType.ALL)//cascade =CascadeType.All means that all operations
    // (persist, merge, remove, refresh, detach) will be cascaded to the associated entity
    private Card card;
}
