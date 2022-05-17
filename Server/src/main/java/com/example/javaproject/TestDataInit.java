package com.example.javaproject;

import com.example.javaproject.repository.JournalRepository;
import com.example.javaproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class TestDataInit implements CommandLineRunner {
    @Autowired
    JournalRepository journalRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder pwdEncoder;

    @Override
    public void run(String... args) throws Exception {
        //userRepository.save(new User("user", pwdEncoder.encode("pwd"), Collections.singletonList("ROLE_USER")));
        //userRepository.save(new User("admin", pwdEncoder.encode("admin"), Collections.singletonList("ROLE_ADMIN")));
    }
}
