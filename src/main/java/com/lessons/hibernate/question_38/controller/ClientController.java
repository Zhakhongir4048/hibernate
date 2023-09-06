package com.lessons.hibernate.question_38.controller;

import com.lessons.hibernate.question_38.dao.ClientJpaRepository;
import com.lessons.hibernate.question_38.entity.Client;
import com.lessons.hibernate.question_38.usecase.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {

    private ClientService clientService;
    private ClientJpaRepository clientJpaRepository;

    @Autowired
    public void autowiredForClientController(ClientService clientService, ClientJpaRepository clientJpaRepository) {
        this.clientService = clientService;
        this.clientJpaRepository = clientJpaRepository;
    }

    @ResponseStatus(OK)
    @GetMapping("/fillDB")
    public String fillDataBase() {
        clientService.generateDB();
        return "Amount clients: " + clientJpaRepository.count();
    }

    @ResponseStatus(OK)
    @GetMapping("/clients-full-name")
    public List<Client> findByFullNameContaining(@RequestParam String name) {
        return clientService.findByFullNameContaining(name);
    }

    @ResponseStatus(OK)
    @GetMapping("/clients-mobile-number")
    public List<Client> findByMobileNumberContaining(@RequestParam String mobileNumber) {
        return clientService.findByMobileNumberContaining(mobileNumber);
    }

}