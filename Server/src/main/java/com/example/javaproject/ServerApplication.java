package com.example.javaproject;

import com.example.javaproject.repository.BookTypesRepository;
import com.example.javaproject.repository.BooksRepository;
import com.example.javaproject.repository.ClientRepository;
import com.example.javaproject.repository.JournalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ServerApplication {

    private static final Logger logger = LoggerFactory.getLogger(ServerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    };

    @Bean
    public CommandLineRunner test(
            JournalRepository journalRepository,
            ClientRepository clientRepository,
            BooksRepository booksRepository,
            BookTypesRepository bookTypesRepository
    ){
        return args -> {
        };
    }
}
