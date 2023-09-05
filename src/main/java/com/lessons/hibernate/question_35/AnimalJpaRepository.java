package com.lessons.hibernate.question_35;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalJpaRepository extends JpaRepository<Animal, Long> {
}