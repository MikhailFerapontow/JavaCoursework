package com.example.javaproject.web;

import com.example.javaproject.DTO.BookDTO;
import com.example.javaproject.Entity.Book;
import com.example.javaproject.exception.ObjectNotPeresented;
import com.example.javaproject.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Book>> getAll(){
        List<Book> books = bService.bookList();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Book> getById(@PathVariable int id){
        Book book = bService.findBook(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/getByCnt/{cnt}")
    public ResponseEntity<Book> getByCntGreater(@PathVariable int cnt){
        Book book = bService.findFirstByCntGreaterThan(cnt);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping(path = "/getByGenre/{name}")
    public ResponseEntity<List<Book>> getByGenre(@PathVariable String name){
        List<Book> books = bService.findByGenre(name);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/getByGenreId/{id}")
    public ResponseEntity<List<Book>> getByGenreId(@PathVariable int id){
        List<Book> books = bService.findByGenreId(id);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Book> add(@RequestBody BookDTO bookDTO){
        try{
            Book book = bService.add(bookDTO);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (ObjectNotPeresented e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
