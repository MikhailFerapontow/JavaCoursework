package com.example.javaproject.DTO;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@JsonSerialize
public class JournalDTO {
    private int bookId;
    private int clientId;
    private Timestamp dateBeg;
    private Timestamp dateEnd;
    private Timestamp dateRet;

    public JournalDTO(int bookId, int clientId, Timestamp dateBeg, Timestamp dateEnd, Timestamp dateRet) {
        this.bookId = bookId;
        this.clientId = clientId;
        this.dateBeg = dateBeg;
        this.dateEnd = dateEnd;
        this.dateRet = dateRet;
    }
}
