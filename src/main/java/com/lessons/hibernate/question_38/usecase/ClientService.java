package com.lessons.hibernate.question_38.usecase;

import com.github.javafaker.Faker;
import com.lessons.hibernate.question_38.dao.ClientJpaRepository;
import com.lessons.hibernate.question_38.entity.Client;
import com.lessons.hibernate.question_38.entity.EmailAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ClientService {

    private ClientJpaRepository clientJpaRepository;

    @Autowired
    public void setClientJpaRepository(ClientJpaRepository clientJpaRepository) {
        this.clientJpaRepository = clientJpaRepository;
    }

    public void generateDB() {
        clientJpaRepository.saveAll(create2000Clients());
    }

    public List<Client> create2000Clients() {
        List<Client> clients = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 0; i < 2000; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String suffixTel = String.valueOf(i);
            String telephone = "+375290000000";
            List<EmailAddress> emailAddresses = Arrays.asList(
                    new EmailAddress((firstName + lastName).toLowerCase() + "1" + i + "@gmail.com"),
                    new EmailAddress((firstName + lastName).toLowerCase() + "2" + i + "@gmail.com"));
            telephone = telephone.substring(0, telephone.length() - suffixTel.length()) + suffixTel;
            Client client = new Client(
                    firstName + " " + lastName,
                    telephone,
                    emailAddresses
            );
            for (EmailAddress emailAddress : emailAddresses) {
                emailAddress.setClient(client);
            }
            clients.add(client);
        }
        return clients;
    }

}