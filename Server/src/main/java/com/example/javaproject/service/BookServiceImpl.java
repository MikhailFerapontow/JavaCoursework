package com.example.javaproject.service;

import com.example.javaproject.DTO.BookDTO;
import com.example.javaproject.Entity.Book;
import com.example.javaproject.Entity.BookType;
import com.example.javaproject.exception.ObjectNotPeresented;
import com.example.javaproject.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BooksRepository bRepository;
    @Autowired
    private BookTypeService btService;

    @Override
    public List<Book> bookList() {
        return (List<Book>) bRepository.findAll();
    }

    @Override
    public Book findBook(int id) {
        Optional<Book> optionalBooks = bRepository.findById(id);

        if(optionalBooks.isPresent()) {
            return optionalBooks.get();
        } else {
            throw new ObjectNotPeresented("Book not found");
        }
    }

    @Override
    public Book findFirstByCntGreaterThan(int num){
        Optional<Book> optionalBook = bRepository.findFirstByCntGreaterThan(num);
        if(optionalBook.isPresent()){
            return optionalBook.get();
        } else {
            throw new ObjectNotPeresented("Book not found");
        }
    }

    @Override
    public List<Book> findByGenre(String name){
        return (List<Book>) bRepository.findBookByBookTypeName(name);
    }

    @Override
    public List<Book> findByGenreId(int num){
        return (List<Book>) bRepository.findBookByBookTypeId(num);
    }

    @Override
    public Book add(BookDTO bookDTO) {
        String name = bookDTO.getName();
        int cnt = bookDTO.getCnt();
        int bookTypeId = bookDTO.getBookTypeId();

        BookType bookType = btService.findById(bookTypeId);
        Book book = new Book(name, cnt, bookType);
        bRepository.save(book);
        return book;
    }
}
