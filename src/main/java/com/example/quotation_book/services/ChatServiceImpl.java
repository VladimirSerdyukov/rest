package com.example.quotation_book.services;

import com.example.quotation_book.models.Chat;
import com.example.quotation_book.repositories.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService {

    ChatRepository chatRepository;

    @Autowired
    public ChatServiceImpl(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public Optional<Chat> findByChatIdEquals(Long chatId){
        return chatRepository.findByChatIdEquals(chatId);
    }

    public Chat save(Chat chat) {
        return chatRepository.save(chat);
    }
}
