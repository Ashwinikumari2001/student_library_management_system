package com.project.studentLibraryManagement.Models;

import com.project.studentLibraryManagement.Enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "Transaction_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "fine")
    private int fine;
    @Column(name = "transaction_date",nullable = false)
    @CreationTimestamp//this annotation is used to automatically set the current date and time when the transaction is created
    private Date transactionDate;
    @Column(name = "due_date",nullable = false)
    private Date dueDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type",nullable = false)
    private TransactionType transactionType;


    @ManyToOne
    private Card card;

    @ManyToOne(cascade = CascadeType.ALL)
    private Book book;


}
