package com.lessons.hibernate.question_36;

import com.lessons.hibernate.question_36.dao.ProductJpaRepository;
import com.lessons.hibernate.question_36.entity.Product;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.lessons.hibernate.question_36.specification.ProductSpecification.*;

@Slf4j
@Service
@Transactional
public class Question36Test {

    @PersistenceContext
    private EntityManager entityManager;
    private ProductJpaRepository productJpaRepository;

    @Autowired
    public void setProductJpaRepository(ProductJpaRepository productJpaRepository) {
        this.productJpaRepository = productJpaRepository;
    }

    @PostConstruct
    public void test() {
        saveAllProducts();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);
        Predicate name = builder.equal(root.get("name"), "Sony");
        Predicate age = builder.equal(root.get("age"), 100);
        query.where(builder.and(name, age));
        List<Product> productListCustom = entityManager.createQuery(query.select(root)).getResultList();
        List<Product> productListWithSpecificationExample1 = productJpaRepository.findAll(equalsName("Apple")
                .or(salaryGreaterThan(4900000)));
        List<Product> productListWithSpecificationExample2 = productJpaRepository.findAll(equalsName("Test")
                .or(salaryLessThan(3000000)));
        log.info("""
                                
                ************************************************************
                Starting Question36Test
                productListCustom = {}
                productListWithSpecificationExample1 = {}
                productListWithSpecificationExample2 = {}
                ************************************************************
                """, productListCustom, productListWithSpecificationExample1, productListWithSpecificationExample2);
        productJpaRepository.deleteAll();
    }

    private void saveAllProducts() {
        Product productSony = new Product();
        productSony.setAge((short) 100);
        productSony.setName("Sony");
        productSony.setSalary(1000000);
        Product productApple = new Product();
        productApple.setAge((short) 150);
        productApple.setName("Apple");
        productApple.setSalary(2000000);
        Product productHp = new Product();
        productHp.setAge((short) 250);
        productHp.setName("HP");
        productHp.setSalary(3000000);
        Product productGoogle = new Product();
        productGoogle.setAge((short) 350);
        productGoogle.setName("Google");
        productGoogle.setSalary(5000000);
        productJpaRepository.saveAll(List.of(productSony, productApple, productHp, productGoogle));
    }

}