package com.example.javaproject.service;

import com.example.javaproject.DTO.BookTypeDTO;
import com.example.javaproject.Entity.BookType;
import com.example.javaproject.exception.ObjectNotPeresented;
import com.example.javaproject.repository.BookTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookTypeServiceImpl implements BookTypeService {

    @Autowired
    private BookTypesRepository btRepository;

    @Override
    public List<BookType> BookTypesList() {
        return (List<BookType>) btRepository.findAll();
    }

    @Override
    public BookType findById(int id) {
        Optional<BookType> bookTypeOptional = btRepository.findById(id);
        if (bookTypeOptional.isPresent()){
            return bookTypeOptional.get();
        } else {
            throw new ObjectNotPeresented("BookType not found");
        }
    }

    @Override
    public BookType findByName(String name) {
        Optional<BookType> optionalBookType = btRepository.findBookTypeByName(name);
        if(optionalBookType.isPresent()) {
            return optionalBookType.get();
        } else {
            throw new ObjectNotPeresented("BookType not found");
        }
    }

    @Override
    public List<BookType> findByNamePrefix(String prefix){
        return (List<BookType>) btRepository.findAllByNameStartingWith(prefix);
    }

    @Override
    public void updateBookTypeName(int id, String name) {
        btRepository.updateBookTypeName(id, name);
    }

    @Override
    public BookType add(BookTypeDTO bookTypeDTO) {
        String name = bookTypeDTO.getName();
        int cnt = bookTypeDTO.getCnt();
        int fine = bookTypeDTO.getFine();
        int dayCount = bookTypeDTO.getDayCount();
        BookType bookType = new BookType(name, cnt, fine, dayCount);
        btRepository.save(bookType);
        return bookType;
    }
}
