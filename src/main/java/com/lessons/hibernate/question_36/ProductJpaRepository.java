package com.lessons.hibernate.question_36;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductJpaRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
}