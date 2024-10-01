package com.example.quotation_book.repositories;

import com.example.quotation_book.models.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface ChatRepository extends JpaRepository<Chat, Long> {
    Optional<Chat> findByChatIdEquals(Long chatId);
}
