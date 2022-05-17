package com.example.javaproject.service;

import com.example.javaproject.DTO.JournalDTO;
import com.example.javaproject.Entity.Journal;

import java.sql.Timestamp;
import java.util.List;

public interface JournalService {
    List<Journal> listJournal();
    Journal findJournal(int id);
    List<Journal> findByClientName(String firstName);
    List<Journal> findByBookName(String name);
    List<Journal> findByClientId(int id);
    Journal add(JournalDTO journalDTO);
    Journal addJournal(Journal journal);
}
