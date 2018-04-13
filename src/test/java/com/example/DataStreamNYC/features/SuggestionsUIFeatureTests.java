package com.example.DataStreamNYC.features;

import com.example.DataStreamNYC.models.Suggestion;
import com.example.DataStreamNYC.models.User;
import com.example.DataStreamNYC.repositories.SuggestionRepository;
import com.example.DataStreamNYC.repositories.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SuggestionsUIFeatureTests {

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
    public void shouldAllowFullCrudFunctionalityForAUser() throws Exception {

        Suggestion firstSuggestion = new Suggestion(
                "title1",
                "suggestionContent1",
                "secondUser"
        );
        firstSuggestion = suggestionRepository.save(firstSuggestion);
        Long firstSuggestionId = firstSuggestion.getId();

        Suggestion secondSuggestion = new Suggestion(
                "title2",
                "suggestionContent2",
                "fifthUser"
        );
        secondSuggestion = suggestionRepository.save(secondSuggestion);
        Long secondSuggestionId = secondSuggestion.getId();

        System.setProperty("selenide.browser", "Chrome");

        // Visit the UI in a browser
        open("http://localhost:3000/");

        // Visit the admin view page
        $("#suggestions-link").click();

        // Make sure the link worked and the form is now showing
        $("#suggestions-wrapper").should(appear);

        // There should only be two users
        $$("[data-suggestion-display]").shouldHave(size(2));

        // Test that all data shows up for each user
        $("#suggestion-" + firstSuggestionId + "-title").shouldHave(text("title1"));
        $("#suggestion-" + firstSuggestionId + "-content").shouldHave(text("suggestionContent1"));
        $("#suggestion-" + firstSuggestionId + "-userName").shouldHave(text("secondUser"));

        $("#suggestion-" + secondSuggestionId + "-title").shouldHave(text("suggestion2First"));
        $("#suggestion-" + secondSuggestionId + "-content").shouldHave(text("suggestion2Last"));
        $("#suggestion-" + secondSuggestionId + "-userName").shouldHave(text("fifthUser"));
    }
}
