package com.example.javaproject.web;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonSerialize
public class AuthRequest{
    private String userName;
    private String password;

    public AuthRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
