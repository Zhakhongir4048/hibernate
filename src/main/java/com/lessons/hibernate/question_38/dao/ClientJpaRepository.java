package com.lessons.hibernate.question_38.dao;

import com.lessons.hibernate.question_38.entity.Client;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientJpaRepository extends JpaRepository<Client, Long> {

    /**
     * Мы будем искать клиентов по части имени
     */
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = "emailAddresses")
    List<Client> findByFullNameContaining(String name);

    /**
     * Мы будем искать клиентов по части телефонного номера
     */
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "client_entity-graph")
    List<Client> findByMobileNumberContaining(String mobile);

}