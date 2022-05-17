package com.example.javaproject.web;

import com.example.javaproject.Entity.Client;
import com.example.javaproject.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientService cService;

    @GetMapping("/getById/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable int id){
        Client client = cService.findClient(id);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @GetMapping(path = "/getByName/{name}")
    public ResponseEntity<Client> getClientByFullName(@PathVariable String name){
        Client client = cService.findClientByName(name);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteCLientById(@PathVariable int id){
        cService.deleteById(id);
    }

    @PutMapping(path = "/updateFullNameById/{id}", consumes = "application/json")
    public void updateClientFullNameById(@PathVariable int id, @RequestBody Map<String, String> json){
        String firstName = json.get("firstName");
        String secondName = json.get("secondName");
        String patherName = json.get("patherName");
        cService.updateName(id, firstName, secondName, patherName);
    }

    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Client> add(@RequestBody Map<String, String> json){
        String firstName = json.get("first_name");
        String secondName = json.get("second_name");
        String patherName = json.get("pather_name");
        String passportSeria = json.get("passport_seria");
        String passportNum = json.get("passport_num");
        Client client = new Client(firstName, secondName, patherName, passportSeria, passportNum);
        cService.add(client);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }
}
