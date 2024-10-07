package com.example.quotation_book.controllers;

import com.example.quotation_book.models.Quote;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@RequestMapping("/api")
public interface ApiController {
    @GetMapping("/all")
    ResponseEntity<List<Quote>> getAll(@RequestParam(required = false, defaultValue = "1") String page);
    @GetMapping("/page")
    ResponseEntity<List<Quote>> getPage(@RequestParam(required = false, defaultValue = "1") String page);
    @GetMapping("/{id}")
    ResponseEntity<Quote> getById(@PathVariable("id") int id);
    @GetMapping("/random")
    ResponseEntity<Quote> getRandom();
}
