package com.example.javaproject.service;

import com.example.javaproject.Entity.Client;

import java.util.List;

public interface ClientService {
    List<Client> clientList();

    Client findClient(int id);
    Client findClientByName(String name);

    Client add(Client client);
    void deleteById(int id);
    void updateName(int id, String name, String secondName, String patherName);
}
