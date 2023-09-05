package com.lessons.hibernate.question_36;

import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    public static Specification<Product> equalsName(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"), name);
    }

}