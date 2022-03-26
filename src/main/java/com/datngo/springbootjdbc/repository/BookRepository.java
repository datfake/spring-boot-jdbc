package com.datngo.springbootjdbc.repository;

import com.datngo.springbootjdbc.model.Book;

import java.util.List;

public interface BookRepository {
    int save(Book book);
    int update(Book book);
    Book findById(Long id);
    int deleteById(Long id);
    List<Book> findAll();
    List<Book> findByNameContaining(String title);
    int deleteAll();
}
