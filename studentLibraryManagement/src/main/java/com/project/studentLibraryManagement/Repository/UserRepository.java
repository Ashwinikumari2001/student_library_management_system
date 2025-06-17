package com.project.studentLibraryManagement.Repository;

import com.project.studentLibraryManagement.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

     User findByEmailAndPassward(String email, String passward);
}
