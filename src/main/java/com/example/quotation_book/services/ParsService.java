package com.example.quotation_book.services;

import java.util.Map;

public interface ParsService {
    Map<Integer, String> getPage(int page);
    Map.Entry<Integer, String> getById(int id);
    Map.Entry<Integer, String> getRandom();
}
