package com.project.studentLibraryManagement.Repository;

import com.project.studentLibraryManagement.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    // Optional data structure handle the null case. If student present on DB then it stores the student else stores null
    Optional<Student> findByEmail(String email); // findBy -> select * from student_table where email = :email;
}
