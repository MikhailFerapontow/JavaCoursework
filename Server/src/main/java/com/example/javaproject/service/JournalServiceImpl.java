package com.example.javaproject.service;

import com.example.javaproject.DTO.JournalDTO;
import com.example.javaproject.Entity.Book;
import com.example.javaproject.Entity.Client;
import com.example.javaproject.Entity.Journal;
import com.example.javaproject.exception.ObjectNotPeresented;
import com.example.javaproject.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class JournalServiceImpl implements JournalService {

    @Autowired
    private JournalRepository jRepository;
    @Autowired
    private BookService bService;
    @Autowired
    private ClientService cService;


    @Override
    public List<Journal> listJournal() {
        return (List<Journal>) jRepository.findAll();
    }

    @Override
    public Journal findJournal(int id) {
        Optional<Journal> optionalJournal = jRepository.findById(id);

        if(optionalJournal.isPresent())
        {
            return optionalJournal.get();
        }
        else {
            throw new ObjectNotPeresented("journal not found");
        }
    }

    @Override
    public List<Journal> findByClientName(String firstName){
        return (List<Journal>) jRepository.findAllByClientName(firstName);
    }

    @Override
    public List<Journal> findByBookName(String name){
        return (List<Journal>) jRepository.findAllByBookName(name);
    }

    @Override
    public List<Journal> findByClientId(int id){
        return (List<Journal>) jRepository.findAllByClientId(id);
    }

    @Override
    public Journal add(JournalDTO journalDTO) {
        int bookId = journalDTO.getBookId();
        int clientId = journalDTO.getClientId();
        Timestamp dateBeg = journalDTO.getDateBeg();
        Timestamp dateEnd = journalDTO.getDateEnd();
        Timestamp dateRet = journalDTO.getDateRet();

        Book book = bService.findBook(bookId);
        Client client = cService.findClient(clientId);
        Journal journal = new Journal(book, client, dateBeg, dateEnd, dateRet);
        jRepository.save(journal);
        return journal;
    }

    @Override
    public Journal addJournal(Journal journal) {
        return jRepository.save(journal);
    }
}
