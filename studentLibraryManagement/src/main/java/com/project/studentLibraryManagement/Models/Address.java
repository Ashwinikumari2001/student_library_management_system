package com.project.studentLibraryManagement.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "address_table")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {
    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "country",nullable = false)
    private String country;
    @Column(name = "state",nullable = false)
    private String state;
    @Column(name = "city",nullable = false)
    private String city;
    @Column(name = "locality",nullable = false)
    private String locality;
    @Column(name = "area",nullable = false)
    private String area;
    @Column(name = "pincode",nullable = false)
    private String pincode;

    @OneToOne
//    @JoinColumn(referencedColumnName = "email")
    private Student student;

    @OneToOne
    private  Author author;
}
