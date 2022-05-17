package com.example.javaproject.DTO;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonSerialize
public class BookTypeDTO {
    private String name;
    private int cnt;
    private int fine;
    private int dayCount;

    public BookTypeDTO(String name, int cnt, int fine, int dayCount) {
        this.name = name;
        this.cnt = cnt;
        this.fine = fine;
        this.dayCount = dayCount;
    }
}
