package com.scibite.person;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Setter
@NoArgsConstructor
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

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = 0;
        this.favouriteColour = "pink";
        this.hobby = new ArrayList<>();
    }

    public void updateDetails(Person updatedPerson) {
        this.firstName = updatedPerson.firstName;
        this.lastName = updatedPerson.lastName;
        this.age = updatedPerson.age;
        this.favouriteColour = updatedPerson.favouriteColour;
        this.hobby = updatedPerson.hobby;
    }

    @Override
    public String toString() {
        return "firstName: " + firstName + ", lastName: " + lastName;
    }

}