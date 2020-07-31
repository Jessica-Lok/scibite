package com.scibite;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;

public class Person {

    @Id
    public String id;

    @Getter
    @Setter
    @JsonProperty("first_name")
    private String firstName;

    @Getter
    @Setter
    @JsonProperty("last_name")
    private String lastName;

    @Getter
    @Setter
    private int age;

    @Getter
    @Setter
    @JsonProperty("favourite_colour")
    private String favouriteColour;

    @Getter
    @Setter
    private List<String> hobby;

    public Person(String firstName, String lastName, int age, String favouriteColour, List<String> hobby) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.favouriteColour = favouriteColour;
        this.hobby = hobby;
    }

    public void updateDetails(Person updatedPerson) {
        this.firstName = updatedPerson.getFirstName();
        this.lastName = updatedPerson.getLastName();
        this.age = updatedPerson.getAge();
        this.favouriteColour = updatedPerson.getFavouriteColour();
        this.hobby = updatedPerson.getHobby();
    }

}