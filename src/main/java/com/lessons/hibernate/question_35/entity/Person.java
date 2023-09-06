package com.lessons.hibernate.question_35.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "persons_question_35")
@Getter
@Setter
@ToString
@NamedQuery(name = "get_person_by_name",
        query = "select p from Person p where name = :name")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "salary")
    private Integer salary;

    @Column(name = "age")
    private Byte age;

}