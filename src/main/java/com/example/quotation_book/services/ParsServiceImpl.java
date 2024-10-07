package com.example.quotation_book.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

@Component
public class ParsServiceImpl implements ParsService {

    public Map<Integer, String> getPage(int page) {
        Map<Integer, String> quotes = new HashMap<>();
        try {
            Document doc = Jsoup.connect("http://ibash.org.ru/?page=" + page).get();
            Elements sourceQuote = doc.select(".quote");
            for (Element quote : sourceQuote) {
                int id = Integer.parseInt(quote.select("b").first().text().substring(1));
                String text = StringEscapeUtils.unescapeHtml4(quote.select(".quotbody").first().text());
                quotes.put(id, text);
            }
        } catch (IOException ignored) {
        }
        return quotes;
    }

    public Map.Entry<Integer, String> getById(int id) {
        try {
            Document doc = Jsoup.connect("http://ibash.org.ru/quote.php?=" + id).get();
            Element sourceQuote = doc.select(".quote").first();
            String realId = sourceQuote.select("b").first().text();
            if (realId.equals("#???")) return null;
            String text = sourceQuote.select(".quotbody").first().text();
            return new AbstractMap.SimpleEntry<>(id, text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map.Entry<Integer, String> getRandom() {
        try {
            Document doc = Jsoup.connect("http://ibash.org.ru/random.php").get();
            Element sourceQuote = doc.select(".quote").first();
            String realId = sourceQuote.select("b").first().text();
            if (realId.equals("#???")) return null;
            String text = sourceQuote.select(".quotbody").first().text();
            System.out.println("RealId: " + realId + " text: " + text);
            return new AbstractMap.SimpleEntry<>(Integer.parseInt(realId.substring(1)), text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
