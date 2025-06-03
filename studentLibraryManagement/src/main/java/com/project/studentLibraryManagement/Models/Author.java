package com.project.studentLibraryManagement.Models;

import com.project.studentLibraryManagement.Enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Author_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "author_name",nullable = false)
    private String name;
    @Column(name = "author_email",nullable = false,unique = true)
    private String email;
    @Column(name = "rating")
    private double rating;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToOne(mappedBy = "author",cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy = "author")
    private List<Book> book;

}
