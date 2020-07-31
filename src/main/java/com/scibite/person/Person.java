package com.scibite.person;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.util.List;

public class Person {

    @Id
    public String id;

    @Getter
    @JsonProperty("first_name")
    private String firstName;

    @Getter
    @JsonProperty("last_name")
    private String lastName;

    @Getter
    private int age;

    @Getter
    @JsonProperty("favourite_colour")
    private String favouriteColour;

    @Getter
    private List<String> hobby;

    public Person(String firstName, String lastName, int age, String favouriteColour, List<String> hobby) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.favouriteColour = favouriteColour;
        this.hobby = hobby;
    }

    public void updateDetails(Person updatedPerson) {
        this.firstName = updatedPerson.firstName;
        this.lastName = updatedPerson.lastName;
        this.age = updatedPerson.age;
        this.favouriteColour = updatedPerson.favouriteColour;
        this.hobby = updatedPerson.hobby;
    }

}