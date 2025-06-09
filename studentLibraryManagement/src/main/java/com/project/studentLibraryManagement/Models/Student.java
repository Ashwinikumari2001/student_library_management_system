package com.project.studentLibraryManagement.Models;

import com.project.studentLibraryManagement.Enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "Student_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "student_id")
    private int id;

    @Column(name = "student_name",nullable = false)
    private  String name;
    @Column(name = "student_email",nullable = false,unique = true)
    private String email;
    @Column(name = "student_mobile",nullable = false, length = 10)
    private String mobile;
    @Column(name = "student_dept",nullable = false)
    private String department;
    @Column(name = "student_sem",nullable = false)
    private String semester;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "student_gender",nullable = false)
    private Gender gender;
    @Column(name = "student_dob",nullable = false)
    private Date dateOfBirth;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    private Address address;

    @OneToOne(mappedBy = "student",cascade = CascadeType.ALL)
    private Card card;

    //cascade =CascadeType.All means that all operations
    // (persist, merge, remove, refresh, detach) will be cascaded to the associated entity
}
