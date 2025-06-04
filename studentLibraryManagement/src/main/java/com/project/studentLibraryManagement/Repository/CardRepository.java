package com.project.studentLibraryManagement.Repository;

import com.project.studentLibraryManagement.Enums.CardStatus;
import com.project.studentLibraryManagement.Models.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card,Integer> {
    List<Card> findByCardStatus(CardStatus cardStatus);
}
