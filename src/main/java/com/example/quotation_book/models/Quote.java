package com.example.quotation_book.models;

import jakarta.persistence.*;

@Entity
@Table(name = "\"Quotes\"")
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    //@Lob
    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "\"quoteId\"", nullable = false)
    private Integer quoteId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(Integer quoteId) {
        this.quoteId = quoteId;
    }
}
