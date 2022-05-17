package com.example.javaproject.web;

import com.example.javaproject.DTO.BookTypeDTO;
import com.example.javaproject.Entity.BookType;
import com.example.javaproject.service.BookTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bookType")
public class BookTypeController {
    @Autowired
    private BookTypeService btService;

    @GetMapping("/getAll")
    public ResponseEntity<List<BookType>> getAll(){
        List<BookType> bookTypes = btService.BookTypesList();
        return new ResponseEntity<>(bookTypes, HttpStatus.OK);
    }

    @GetMapping(path = "/getByName/{name}")
    public ResponseEntity<BookType> getByName(@PathVariable String name){
        BookType bookType = btService.findByName(name);
        return new ResponseEntity<>(bookType, HttpStatus.OK);
    }

    @GetMapping(path = "/getByPrefix/{prefix}")
    public ResponseEntity<List<BookType>> getByPrefix(@PathVariable String prefix){
        List<BookType> bookTypes = btService.findByNamePrefix(prefix);
        return new ResponseEntity<>(bookTypes, HttpStatus.OK);
    }

    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<BookType> add(@RequestBody BookTypeDTO bookTypeDTO){
        BookType bookType = btService.add(bookTypeDTO);
        return new ResponseEntity<>(bookType, HttpStatus.OK);
    }

    @PutMapping(path = "/updateName/{id}", consumes = "application/json")
    void updateName(@PathVariable int id, @RequestBody Map<String, String> json){
        String name = json.get("name");
        btService.updateBookTypeName(id, name);
    }
}
