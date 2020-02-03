package xyz.dynnoil.demo.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.dynnoil.demo.entiries.Greeting;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloRestControllerIT {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void testGreetWithoutName() {
        ResponseEntity<Greeting> response = template.getForEntity("/rest", Greeting.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        Greeting body = response.getBody();
        assertEquals("Hello, World!", body.getMessage());
    }

    @Test
    public void testGreetWithName() {
        Greeting body = template.getForObject("/rest?name=Oleg", Greeting.class);
        assertEquals("Hello, Oleg!", body.getMessage());
    }
}
