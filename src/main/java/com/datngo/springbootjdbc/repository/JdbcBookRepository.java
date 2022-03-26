package com.datngo.springbootjdbc.repository;

import com.datngo.springbootjdbc.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcBookRepository implements BookRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Book book) {
        return jdbcTemplate.update("INSERT INTO book (name, description) VALUES (?, ?)",
                new Object[] {book.getName(), book.getDescription()});

    }

    @Override
    public int update(Book book) {
        return jdbcTemplate.update("UPDATE book set name = ?, description = ? where id = ?",
                new Object[]{book.getName(), book.getDescription(), book.getId()});
    }

    @Override
    public Book findById(Long id) {
        try {
            Book tutorial = jdbcTemplate.queryForObject("SELECT * FROM book WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Book.class), id);
            return tutorial;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
    }

    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public List<Book> findByNameContaining(String title) {
        return null;
    }

    @Override
    public int deleteAll() {
        return 0;
    }
}
