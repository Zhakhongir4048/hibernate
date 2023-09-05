package com.lessons.hibernate.question_35;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class Question35Test {

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    public void test() {
        Query query = entityManager
                .createQuery("select p from Person p where p.name = :name")
                .setParameter("name", "Zhakhongir");
        TypedQuery<Person> typedQuery = entityManager
                .createQuery("select p from Person p where p.age  = 24", Person.class);
        Query namedQueryFromQuery = entityManager
                .createNamedQuery("get_person_by_name")
                .setParameter("name", "Zhakhongir");
        TypedQuery<Person> namedQueryFromTypedQuery = entityManager
                .createNamedQuery("get_person_by_name", Person.class)
                .setParameter("name", "Zhakhongir");
        log.info("""
                                        
                        ************************************************************
                        Starting Question35Test
                        queryResult = {}
                        typedQueryResult = {}
                        namedQueryFromQuery = {}
                        namedQueryFromTypedQuery = {}
                        ************************************************************
                        """, query.getResultList(), typedQuery.getResultList(),
                namedQueryFromQuery.getResultList(), namedQueryFromTypedQuery.getResultList());
    }

}
