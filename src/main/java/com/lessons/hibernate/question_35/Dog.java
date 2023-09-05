package com.lessons.hibernate.question_35;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("DOG")
public class Dog extends Animal {
}