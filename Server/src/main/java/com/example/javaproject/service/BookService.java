package com.example.javaproject.service;

import com.example.javaproject.DTO.BookDTO;
import com.example.javaproject.Entity.Book;

import java.util.List;

public interface BookService {
    List<Book> bookList();
    Book findBook(int id);

    Book findFirstByCntGreaterThan(int num);
    List<Book> findByGenre(String name);
    List<Book> findByGenreId(int num);
    Book add(BookDTO bookDTO);
}
