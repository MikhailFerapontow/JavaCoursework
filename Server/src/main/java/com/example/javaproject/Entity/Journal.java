package com.example.javaproject.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "journal")
public class Journal {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "date_beg", nullable = false)
    private Timestamp dateBeg;

    @Column(name = "date_end", nullable = false)
    private Timestamp dateEnd;

    @Column(name = "date_ret", nullable = false)
    private Timestamp dateRet;

    public Journal() {
    }

    public Journal(Book book, Client client, Timestamp dateBeg, Timestamp dateEnd, Timestamp dateRet) {
        this.book = book;
        this.client = client;
        this.dateBeg = dateBeg;
        this.dateEnd = dateEnd;
        this.dateRet = dateRet;
    }

    @Override
    public String toString() {
        return "Journal{" +
                "id=" + id +
                ", books=" + book +
                ", client=" + client +
                ", date_beg=" + dateBeg +
                ", date_end=" + dateEnd +
                ", date_ret=" + dateRet +
                '}';
    }
}
