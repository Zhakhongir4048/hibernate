package com.lessons.hibernate.question_38.dao;

import com.lessons.hibernate.question_38.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientJpaRepository extends JpaRepository<Client, Long> {
}