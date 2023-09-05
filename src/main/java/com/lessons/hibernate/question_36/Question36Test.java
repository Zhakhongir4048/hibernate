package com.lessons.hibernate.question_36;

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
        List<Product> resultList = entityManager.createQuery(query.select(root)).getResultList();
        log.info("""
                                
                ************************************************************
                Starting Question36Test
                resultList = {}
                ************************************************************
                """, resultList);
    }

    private void saveAllProducts() {
        Product productSony = new Product();
        productSony.setAge((byte) 100);
        productSony.setName("Sony");
        productSony.setSalary(1000000);
        Product productApple = new Product();
        productApple.setAge((byte) 150);
        productApple.setName("Apple");
        productApple.setSalary(2000000);
        Product productHp = new Product();
        productHp.setAge((byte) 250);
        productHp.setName("HP");
        productHp.setSalary(3000000);
        Product productGoogle = new Product();
        productGoogle.setAge((byte) 550);
        productGoogle.setName("Google");
        productGoogle.setSalary(5000000);
        productJpaRepository.saveAll(List.of(productSony, productApple, productHp, productGoogle));
    }

}