package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonModelTests {

    static Person person;

    @BeforeEach
    public void initEachTest() {
        person = new Person("Homer", "Simpson", 39);
    }

    @Test
    public void PersonFirstNameTest(){
        assertEquals("Homer", person.getFirstName());
    }

    @Test
    public void PersonLastNameTest(){
        assertEquals("Simpson", person.getLastName());
    }

    @Test
    public void PersonAgeTest(){
        assertEquals(39, person.getAge());
    }

    @Test
    public void introductionTest(){
        assertEquals("Hello my name is Homer Simpson, I am 39 years old", person.introducePerson());
    }

}
