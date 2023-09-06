package com.lessons.hibernate.question_35.dao;

import com.lessons.hibernate.question_35.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalJpaRepository extends JpaRepository<Animal, Long> {
}