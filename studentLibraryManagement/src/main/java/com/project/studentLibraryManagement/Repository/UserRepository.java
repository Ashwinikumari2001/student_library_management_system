package com.project.studentLibraryManagement.Repository;

import com.project.studentLibraryManagement.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

     Optional<User> findByEmail(String email);
     Optional<User> findByEmailAndPassword(String email, String password);
}
