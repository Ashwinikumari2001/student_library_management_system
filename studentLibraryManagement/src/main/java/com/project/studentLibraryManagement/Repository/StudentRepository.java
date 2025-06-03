package com.project.studentLibraryManagement.Repository;

import com.project.studentLibraryManagement.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {

}
