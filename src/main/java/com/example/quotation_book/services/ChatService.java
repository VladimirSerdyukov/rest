package com.example.quotation_book.services;

import com.example.quotation_book.models.Chat;

import java.util.Optional;

public interface ChatService {
    Optional<Chat> findByChatIdEquals(Long chatId);
    Chat save(Chat chat);

}
