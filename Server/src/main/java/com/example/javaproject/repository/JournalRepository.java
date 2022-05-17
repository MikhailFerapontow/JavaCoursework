package com.example.javaproject.repository;

import com.example.javaproject.Entity.Journal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;

public interface JournalRepository extends CrudRepository<Journal, Integer> {

    @Query("SELECT j FROM Journal j " +
            "JOIN Client cl " +
            "ON j.client = cl " +
            "WHERE cl.firstName = :firstName ")
    Iterable<Journal> findAllByClientName(
            @Param("firstName") String firstName
    );

    @Query("SELECT j FROM Journal j " +
            "JOIN Book bk " +
            "ON j.book = bk " +
            "WHERE bk.name = :name")
    Iterable<Journal> findAllByBookName(
            @Param("name") String name
    );

    @Query("SELECT j FROM Journal j JOIN Client cl ON j.client = cl WHERE cl.id = :id")
    Iterable<Journal> findAllByClientId(
            @Param("id") int id
    );
}
