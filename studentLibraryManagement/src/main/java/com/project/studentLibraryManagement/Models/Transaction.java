package com.project.studentLibraryManagement.Models;

import com.project.studentLibraryManagement.Enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "Transaction_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "fine")
    private int fine;
    @Column(name = "transaction_date",nullable = false)
    private Date transactionDate;
    @Column(name = "due_date",nullable = false)
    private Date dueDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type",nullable = false)
    private TransactionType transactionType;


    @ManyToOne(cascade = CascadeType.PERSIST)
    private Card card;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Book book;
}
