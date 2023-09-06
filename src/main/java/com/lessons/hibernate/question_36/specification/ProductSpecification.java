package com.lessons.hibernate.question_36.specification;

import com.lessons.hibernate.question_36.entity.Product;
import com.lessons.hibernate.question_36.entity.Product_;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    public static Specification<Product> equalsName(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Product_.name), name);
    }

    public static Specification<Product> salaryGreaterThan(Integer salary) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get(Product_.salary), salary);
    }

    public static Specification<Product> salaryLessThan(Integer salary) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThan(root.get(Product_.salary), salary);
    }

}