package com.example.javaproject.DTO;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonSerialize
public class BookDTO {
    private String name;
    private int cnt;
    private int bookTypeId;

    public BookDTO(String name, int cnt, int bookTypeId) {
        this.name = name;
        this.cnt = cnt;
        this.bookTypeId = bookTypeId;
    }
}
