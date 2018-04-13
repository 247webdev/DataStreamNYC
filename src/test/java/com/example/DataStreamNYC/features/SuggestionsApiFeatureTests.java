package com.example.DataStreamNYC.features;

import com.example.DataStreamNYC.models.Suggestion;
import com.example.DataStreamNYC.repositories.SuggestionRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SuggestionsApiFeatureTests {

    @Autowired
    private SuggestionRepository suggestionRepository;

    @Before
    public void setUp() {
        suggestionRepository.deleteAll();
    }

    @After
    public void tearDown() {
        suggestionRepository.deleteAll();
    }

    @Test
    public void shouldAllowFullCrudForASuggestion() throws Exception {

        Suggestion firstSuggestion = new Suggestion(
                "title1",
                "contentSuggestion1",
                "user1"
        );

        Suggestion secondSuggestion = new Suggestion(
                "title2",
                "contentSuggestion2",
                "user2"
        );

        Stream.of(firstSuggestion, secondSuggestion)
                .forEach(suggestion -> {
                    suggestionRepository.save(suggestion);
                });

        // Test creating a Suggestion
        Suggestion suggestionNotYetInDb = new Suggestion(
                "newTitle",
                "newContent",
                "newUser"
        );

        given()
                .contentType(JSON)
                .and().body(suggestionNotYetInDb)
            .when()
                .post("http://localhost:8080/suggestions")
            .then()
                .statusCode(is(200))
                .and().body(containsString("newContent"));

        // Test get all Suggestions
        when()
                .get("http://localhost:8080/suggestions")
            .then()
                .statusCode(is(200))
                .and().body(containsString("title1"))
                .and().body(containsString("contentSuggestion2"))
                .and().body(containsString("newUser"));

        // Test finding one suggestion by ID
        when()
                .get("http://localhost:8080/suggestions/" + secondSuggestion.getId())
            .then()
                .statusCode(is(200))
                .and().body(containsString("title2"))
                .and().body(containsString("contentSuggestion2"))
                .and().body(containsString("user2"));

        // Test updating a suggestion
        secondSuggestion.setTitle("changedTitle");

        given()
                .contentType(JSON)
                .and().body(secondSuggestion)
            .when()
                .patch("http://localhost:8080/suggestions/" + secondSuggestion.getId())
            .then()
                .statusCode(is(200))
                .and().body(containsString("changedTitle"));

        // Test deleting a suggestion
        when()
                .delete("http://localhost:8080/suggestions/" + secondSuggestion.getId())
            .then()
                .statusCode(is(200));
    }
}
