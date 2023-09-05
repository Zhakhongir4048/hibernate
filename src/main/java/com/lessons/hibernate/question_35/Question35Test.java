package com.lessons.hibernate.question_35;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class Question35Test {

    @PersistenceContext
    private EntityManager entityManager;
    private AnimalJpaRepository animalJpaRepository;

    @Autowired
    public void setAnimalJpaRepository(AnimalJpaRepository animalJpaRepository) {
        this.animalJpaRepository = animalJpaRepository;
    }

    /**
     * Java Persistence query language (JPQL)
     */
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
        saveAllAnimals();
        Query queryResultSubclassCat = entityManager
                .createQuery("select a from Animal a where type(a) in(Animal, Cat)");
        log.info("""
                                        
                        ************************************************************
                        Starting Question35Test
                        queryResult = {}
                        typedQueryResult = {}
                        namedQueryFromQuery = {}
                        namedQueryFromTypedQuery = {}
                        queryResultSubclassCat = {}
                        ************************************************************
                        """,
                query.getResultList(),
                typedQuery.getResultList(),
                namedQueryFromQuery.getResultList(),
                namedQueryFromTypedQuery.getResultList(),
                queryResultSubclassCat.getResultList());
    }

    private void saveAllAnimals() {
        Animal dog = new Dog();
        dog.setAge(1);
        dog.setName("Собака");
        Animal cat = new Cat();
        cat.setAge(2);
        cat.setName("Кошка");
        animalJpaRepository.saveAll(List.of(cat, dog));
    }

}
