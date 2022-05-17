package com.example.javaproject.repository;

import com.example.javaproject.Entity.BookType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface BookTypesRepository extends CrudRepository<BookType, Integer> {
    Optional<BookType> findBookTypeByName(String name);

    Iterable<BookType> findAllByNameStartingWith(String prefix);

    Iterable<BookType> findAllByCntBetween(int first, int second);

    @Transactional
    @Modifying
    @Query("UPDATE BookType bt SET bt.name = :newName WHERE bt.id = :id")
    void updateBookTypeName(@Param("id") int id, @Param("newName") String newName);
}
