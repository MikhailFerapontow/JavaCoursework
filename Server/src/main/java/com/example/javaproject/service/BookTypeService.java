package com.example.javaproject.service;

import com.example.javaproject.DTO.BookTypeDTO;
import com.example.javaproject.Entity.BookType;

import java.util.List;

public interface BookTypeService {
    List<BookType> BookTypesList();

    BookType findById(int id);
    BookType findByName(String name);
    List<BookType> findByNamePrefix(String prefix);

    void updateBookTypeName(int id, String name);
    BookType add(BookTypeDTO bookTypeDTO);
}
