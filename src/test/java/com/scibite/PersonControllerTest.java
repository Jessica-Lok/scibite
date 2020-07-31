package com.scibite;

import org.junit.jupiter.api.Test;
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

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ScibiteApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void getPersonTest() {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl("http://localhost:" + port + "/person")
                .queryParam("first_name", "Jane")
                .queryParam("last_name", "Doe");

        assertEquals(this.restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, entity, String.class).getBody()
                ,"{\"id\":\"5f23668da92314526cd0703f\"," +
                "\"age\":23," +
                "\"hobby\":[\"baking\"]," +
                "\"first_name\":\"Jane\"," +
                "\"last_name\":\"Doe\"," +
                "\"favourite_colour\":\"blue\"}");
    }

}
