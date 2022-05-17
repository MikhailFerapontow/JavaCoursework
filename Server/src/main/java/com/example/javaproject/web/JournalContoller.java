package com.example.javaproject.web;

import com.example.javaproject.DTO.JournalDTO;
import com.example.javaproject.Entity.Journal;
import com.example.javaproject.exception.ObjectNotPeresented;
import com.example.javaproject.service.BookService;
import com.example.javaproject.service.ClientService;
import com.example.javaproject.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalContoller {
    @Autowired
    private JournalService jService;
    @Autowired
    private ClientService cService;
    @Autowired
    private BookService bService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Journal>> getAll(){
        List<Journal> journalList = jService.listJournal();
        return new ResponseEntity<>(journalList, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Journal> getJournalById(@PathVariable int id){
        Journal journal = jService.findJournal(id);
        return new ResponseEntity<>(journal, HttpStatus.OK);
    }

    @GetMapping(path = "/getByClientName/{name}")
    public ResponseEntity<List<Journal>> getJournalsByClientName(@PathVariable String name){
        List<Journal> journals = jService.findByClientName(name);
        return new ResponseEntity<>(journals, HttpStatus.OK);
    }

    @GetMapping(path = "/getByBookName/{name}")
    public ResponseEntity<List<Journal>> getJournalByBookName(@PathVariable String name){
        List<Journal> journals = jService.findByBookName(name);
        return new ResponseEntity<>(journals, HttpStatus.OK);
    }

    @GetMapping(path = "/getByClientId/{id}")
    public ResponseEntity<List<Journal>> getJournalByClientId(@PathVariable int id){
        List<Journal> journals = jService.findByClientId(id);
        return new ResponseEntity<>(journals, HttpStatus.OK);
    }

    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Journal> addJournal(@RequestBody JournalDTO journalDTO)
    {
        try {
             Journal journal = jService.add(journalDTO);
            return new ResponseEntity<>(journal, HttpStatus.OK);
        } catch (ObjectNotPeresented e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
