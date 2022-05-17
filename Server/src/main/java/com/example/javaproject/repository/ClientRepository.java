package com.example.javaproject.repository;

import com.example.javaproject.Entity.Client;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Integer> {
    Optional<Client> findClientByFirstName(String FirstName);

    @Transactional
    @Modifying
    @Query("UPDATE Client u " +
           "SET u.firstName = :newFirstName, " +
           " u.secondName = :newSecondName, " +
           " u.patherName = :newPatherName " +
           "WHERE u.id = :id")
    void updateClientFullName(
            @Param("id") int id,
            @Param("newFirstName") String firstName,
            @Param("newSecondName") String secondName,
            @Param("newPatherName") String patherName
    );
}
