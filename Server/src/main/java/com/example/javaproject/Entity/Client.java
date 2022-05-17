package com.example.javaproject.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name", length =  20, nullable = false)
    private String firstName;

    @Column(name = "second_name", length =  20, nullable = false)
    private String secondName;

    @Column(name = "pather_name", length =  20, nullable = false)
    private String patherName;

    @Column(name = "passport_seria", length =  20, nullable = false)
    private String passportSeria;

    @Column(name = "passport_num", length =  20, nullable = false)
    private String passportNum;

    @JsonIgnore
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private Collection<Journal> journals;

    public Client() {
    }

    public Client(String first_name, String second_name, String pather_name, String passport_seria, String passport_num) {
        this.firstName = first_name;
        this.secondName = second_name;
        this.patherName = pather_name;
        this.passportSeria = passport_seria;
        this.passportNum = passport_num;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", first_name='" + firstName + '\'' +
                ", second_name='" + secondName + '\'' +
                ", pather_name='" + patherName + '\'' +
                ", passport_seria='" + passportSeria + '\'' +
                ", passport_num='" + passportNum + '\'' +
                '}';
    }
}
