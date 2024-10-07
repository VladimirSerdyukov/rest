package com.example.quotation_book.services;

import com.example.quotation_book.models.Quote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface QuoteService {
    List<Quote> getPage(int page);
    Quote getById(int id);
    Quote getRandom();
    Page<Quote> findAll(PageRequest request);
}
