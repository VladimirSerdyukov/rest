package com.example.quotation_book.services;

import com.example.quotation_book.models.Quote;
import com.example.quotation_book.repositories.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class QuoteServiceImpl implements QuoteService{
    ParsService parsService;
    QuoteRepository quotRepository;
    @Autowired
    public QuoteServiceImpl (ParsService parsService, QuoteRepository quoteRepository) {
        this.parsService = parsService;
        this.quotRepository = quoteRepository;
    }

    public List<Quote> getPage(int page) {
        List<Quote> ret = new ArrayList<>();
        var mapQuotes = parsService.getPage(page);
        for (Map.Entry<Integer, String> quot : mapQuotes.entrySet()) {
            Quote rawQuote = new Quote();
            rawQuote.setQuoteId(quot.getKey());
            rawQuote.setText(quot.getValue());
            var existed = quotRepository.findQuoteByQuoteIdEquals(rawQuote.getQuoteId());
            if (existed.isEmpty()) {
                ret.add(quotRepository.save(rawQuote));
            } else {
                ret.add(existed.get());
            }
        }
        return ret;
    }

    public Quote getById(int id) {
        System.out.println("id: " + id);
        Optional<Quote> existingQuote = quotRepository.findQuoteByQuoteIdEquals(id);
        if (existingQuote.isPresent())
            return existingQuote.get();
        var quoteEntry = parsService.getById(id);
        if (quoteEntry == null) return null;
        Quote newQuote = new Quote();
        newQuote.setQuoteId(quoteEntry.getKey());
        newQuote.setText(quoteEntry.getValue());
        return quotRepository.save(newQuote);
    }

    public Quote getRandom() {
        var quoteEntry = parsService.getRandom();
        if (quoteEntry == null) return null;

        Optional<Quote> existingQuote = quotRepository.findQuoteByQuoteIdEquals(quoteEntry.getKey());
        if (existingQuote.isPresent())
            return existingQuote.get();

        Quote newQuote = new Quote();
        newQuote.setQuoteId(quoteEntry.getKey());
        newQuote.setText(quoteEntry.getValue());
        return quotRepository.save(newQuote);
    }

    public Page<Quote> findAll(PageRequest request) {
        return quotRepository.findAll(request);
    }
}
