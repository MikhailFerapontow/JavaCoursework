package com.example.javaproject.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "cnt")
    private Integer cnt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    private BookType typeId;

    @JsonIgnore
    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    private Collection<Journal> journals;

    public Book() {
    }

    public Book(String name, Integer cnt, BookType typeId) {
        this.name = name;
        this.cnt = cnt;
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cnt=" + cnt +
                ", book_types=" + typeId +
                '}';
    }
}
