package com.example.javaproject.service;

import com.example.javaproject.Entity.Client;
import com.example.javaproject.exception.ObjectNotPeresented;
import com.example.javaproject.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService{

    @Autowired
    private ClientRepository cRepository;

    @Override
    public List<Client> clientList() {
        return (List<Client>) cRepository.findAll();
    }

    @Override
    public Client findClient(int id) {
        Optional<Client> optionalClient = cRepository.findById(id);

        if(optionalClient.isPresent()) {
            return optionalClient.get();
        } else {
            throw new ObjectNotPeresented("Client not found");
        }
    }

    @Override
    public Client findClientByName(String name) {
        Optional<Client> optionalClient = cRepository.findClientByFirstName(name);
        if (optionalClient.isPresent()){
            return optionalClient.get();
        } else {
            throw new ObjectNotPeresented("Client not found");
        }
    }

    @Override
    public Client add(Client client) {
        cRepository.save(client);
        return client;
    }

    @Override
    public void deleteById(int num){
        cRepository.deleteById(num);
    }

    @Override
    public void updateName(int id, String name, String secondName, String patherName){
        cRepository.updateClientFullName(id, name, secondName, patherName);
    }
}
