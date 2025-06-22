package com.project.studentLibraryManagement.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(name = "userName",nullable = false)
    private String name;

    @Column(name = "userEmail",nullable = false,unique = true)
    private String email;

    @Column(name = "password",nullable = false)
    private String password;
}
