package com.example.javaproject.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "book_types")
public class BookType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 50, unique = true)
    private String name;

    @Column(name = "cnt")
    private int cnt;

    @Column(name = "fine")
    private int fine;

    @Column(name = "day_count")
    private int dayCount;

    @JsonIgnore
    @OneToMany(mappedBy = "typeId", cascade = CascadeType.ALL)
    private Collection<Book> books;

    public BookType() {}

    public BookType(String name, int cnt, int fine, int dayCount) {
        this.name = name;
        this.cnt = cnt;
        this.fine = fine;
        this.dayCount = dayCount;
    }

    @Override
    public String toString() {
        return "BookType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cnt=" + cnt +
                ", fine=" + fine +
                ", dayCount=" + dayCount +
                '}';
    }
}
