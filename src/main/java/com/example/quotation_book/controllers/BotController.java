package com.example.quotation_book.controllers;

import com.example.quotation_book.models.Chat;
import com.example.quotation_book.models.Quote;
import com.example.quotation_book.repositories.ChatRepository;
import com.example.quotation_book.services.QuoteService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class BotController {

    @Autowired
    ChatRepository chatRepository;
    @Autowired
    QuoteService quoteService;
    private final TelegramBot bot;
    public BotController() {
        String token = "6676249918:AAHBSc0XBWM4rb4LXl_G5eXj-4kPsTK3lHg";
        // name "t_e_s_t_i_n_g_j_a_v_a_bot"

        bot = new TelegramBot(token);
        bot.setUpdatesListener(updates -> {
            for(Update update : updates) {
                handleUpdate(update);
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });

    }

    private void handleUpdate(Update update) {
        String text = update.message().text();
        Long chatId = update.message().chat().id();
        System.out.println("chatId: " + chatId);
        Optional<Chat> rawChat = chatRepository.findByChatIdEquals(chatId);
        Chat chat;

        if(rawChat.isPresent()) {
            chat = rawChat.get();
        } else {
            Chat _chat = new Chat();
            _chat.setChatId(chatId);
            _chat.setLastId(2);
            chat = chatRepository.save(_chat);
        }

        switch (text) {
            case "/start":
            case "/next":
                System.out.println("start / next");
                sendNextQuote(chat);
                break;
            case "/prev":
                System.out.println("prev");
                sendPrevQuote(chat);
                break;
            case "/rand":
                System.out.println("rands");
                sendRandomQuote(chat);
                break;
        }
    }

    private void sendRandomQuote(Chat chat) {
        Quote quote = quoteService.getRandom();
        sendText(chat.getChatId(), quote.getText());
    }

    private void sendPrevQuote(Chat chat) {
        Quote quote = null;
        int newId = chat.getLastId();
        while(quote == null) {
            newId--;
            if(newId < 2) newId = 19005;
            quote = quoteService.getById(newId);
        }
        chat.setLastId(quote.getQuoteId());
        chatRepository.save(chat);
        sendText(chat.getId(), quote.getText());
    }

    private void sendNextQuote(Chat chat) {
        Quote quote = null;
        int newId = chat.getLastId();
        while(quote == null) {
            newId++;
            if(newId > 19005) newId = 2;
            quote = quoteService.getById(newId);
        }
        chat.setLastId(quote.getQuoteId());
        chatRepository.save(chat);
        sendText(chat.getChatId(), quote.getText());
    }

    private void sendText(Long chatId, String text) {
        bot.execute(new SendMessage(chatId, text));
    }
}
