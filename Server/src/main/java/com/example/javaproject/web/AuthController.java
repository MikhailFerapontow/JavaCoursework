package com.example.javaproject.web;

import com.example.javaproject.Entity.User;
import com.example.javaproject.repository.UserRepository;
import com.example.javaproject.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    PasswordEncoder pwdEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepository userRepository;

    @PostMapping(value = "/signin", consumes = "application/json", produces = "application/json")
    public String signIn(@RequestBody AuthRequest request) {
        try {
            String name = request.getUserName();
            String password = request.getPassword();
            Optional<User> user = userRepository.findUserByName(name);
            boolean passwordMatch = false;
            if (user.isPresent()) {
                User us = user.get();
                passwordMatch = pwdEncoder.matches(password, us.getPassword());
            }

            if (!passwordMatch)
                throw new BadCredentialsException("Invalid username or password");

            String token = jwtTokenProvider.createToken(
                    name,
                    user.orElseThrow(() -> new UsernameNotFoundException("User not found")).getRoles()
            );

            return token;
        } catch(AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

}
