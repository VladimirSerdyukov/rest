package com.example.quotation_book.repositories;

import com.example.quotation_book.models.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface QuoteRepository extends JpaRepository<Quote, Integer> {
    Optional<Quote> findByQuoteIdEquals(Integer quoteId); // не работает метод

    Optional<Quote> findQuoteByQuoteIdEquals(Integer quoteId);
}
