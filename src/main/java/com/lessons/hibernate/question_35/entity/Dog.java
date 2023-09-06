package com.lessons.hibernate.question_35.entity;

import com.lessons.hibernate.question_35.entity.Animal;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("DOG")
public class Dog extends Animal {
}