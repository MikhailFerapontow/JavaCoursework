package com.example.javaproject.repository;

import com.example.javaproject.Entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BooksRepository extends CrudRepository<Book, Integer> {

    Optional<Book> findFirstByCntGreaterThan(int num);

    @Query("SELECT a FROM Book a " +
            "JOIN BookType ap " +
            "ON a.typeId = ap " +
            "WHERE ap.name = :name")
    Iterable<Book> findBookByBookTypeName(
            @Param("name") String name
    );

    @Query("SELECT a FROM Book a " +
            "JOIN BookType ap " +
            "ON a.typeId = ap " +
            "WHERE ap.id = :id")
    Iterable<Book> findBookByBookTypeId(
            @Param("id") int id
    );
}
