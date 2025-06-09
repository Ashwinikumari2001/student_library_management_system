package com.project.studentLibraryManagement.Models;

import com.project.studentLibraryManagement.Enums.CardStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;


@Entity
@Table(name = "Card_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "card_id")
    private int id;
    @Enumerated(EnumType.STRING)
    @Column(name = "card_status",nullable = false)
    private CardStatus cardStatus;
    @Column(name = "created_date",nullable = false)
//    @CreationTimestamp//this annotation is used to automatically set the current date and time when the card is created
    private Date createdDate;
    @Column(name = "updated_date",nullable = false)
//    @UpdateTimestamp
    private Date updatedDate;
    @Column(name = "expiry_date",nullable = false)
    private Date expiryDate;

    @OneToOne
    private Student student;

    @OneToMany(mappedBy = "card",cascade = CascadeType.ALL)
    private List<Transaction> transactions;
    @OneToMany(mappedBy = "card")
    private List<Book> books;
}
