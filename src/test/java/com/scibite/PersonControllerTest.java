package com.scibite;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ScibiteApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Autowired
    private PersonRepository repository;

    private String testEntryID;

    @BeforeAll
    void setUp() {
        Person person = new Person("John",
                "Smith",
                24,
                "red",
                Collections.singletonList("football"));

        repository.save(person);
        testEntryID = person.id;
    }

    @Test
    public void getPersonTest() throws JSONException {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl("http://localhost:" + port + "/person")
                .queryParam("first_name", "John")
                .queryParam("last_name", "Smith");

        JSONObject expectedJson = new JSONObject()
                .put("first_name", "John")
                .put("last_name", "Smith")
                .put("age", 24)
                .put("favourite_colour", "red")
                .put("hobby", new JSONArray().put("football"));

        JSONObject responseJson = new JSONObject(this.restTemplate.exchange(builder.build().toUri(),
                HttpMethod.GET,
                entity,
                String.class)
                .getBody());
        responseJson.remove("id");

        assertEquals(expectedJson.toString(), responseJson.toString());
    }

    @Test
    public void putPersonTest() throws JSONException, JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        JSONObject json = new JSONObject()
                .put("first_name", "John")
                .put("last_name", "Smith")
                .put("age", 24)
                .put("favourite_colour", "red")
                .put("hobby", new JSONArray().put("football"));

        HttpEntity<String> entity = new HttpEntity<>(json.toString(), headers);

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl("http://localhost:" + port + "/person/")
                .queryParam("id", testEntryID);

        restTemplate.exchange(builder.build().toUri(),
                HttpMethod.PUT,
                entity,
                String.class);

        Person updatedPerson = repository.findById(testEntryID).get();

        ObjectMapper mapper = new ObjectMapper();
        JSONObject updatedPersonJson = new JSONObject(mapper.writeValueAsString(updatedPerson));
        updatedPersonJson.remove("id");

        assertEquals(json.toString(), updatedPersonJson.toString());
    }

    @AfterAll
    void tearDown() {
        repository.deleteById(testEntryID);
    }

}
