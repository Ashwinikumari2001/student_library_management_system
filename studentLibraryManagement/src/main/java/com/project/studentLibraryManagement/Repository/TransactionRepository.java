package com.project.studentLibraryManagement.Repository;

import com.project.studentLibraryManagement.Models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

}
