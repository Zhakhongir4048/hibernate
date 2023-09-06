package com.lessons.hibernate.question_38.controller;

import com.lessons.hibernate.question_38.dao.ClientJpaRepository;
import com.lessons.hibernate.question_38.usecase.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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

}