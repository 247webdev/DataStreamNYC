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
    public void shouldAllowMostCrudFunctionalityForAUser() throws Exception {

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

        // Visit the suggestions view page
        $("#suggestions-link").click();

        // Make sure the link worked and the suggestions are now showing
        $("#suggestions-wrapper").should(appear);

        // There should only be two suggestions
        $$("[data-suggestion-display]").shouldHave(size(2));

        // Test that all data shows up for each suggestion
        $("#suggestion-" + firstSuggestionId + "-title").shouldHave(text("title1"));
        $("#suggestion-" + firstSuggestionId + "-content").shouldHave(text("suggestionContent1"));
        $("#suggestion-" + firstSuggestionId + "-userName").shouldHave(text("secondUser"));

        $("#suggestion-" + secondSuggestionId + "-title").shouldHave(text("suggestion2First"));
        $("#suggestion-" + secondSuggestionId + "-content").shouldHave(text("suggestion2Last"));
        $("#suggestion-" + secondSuggestionId + "-userName").shouldHave(text("fifthUser"));

        // Add User name
        $("#new-suggestion-user-name").sendKeys("newUserName");
        $("#user-name-submit").click();

        // Add a new suggestion
        $("#new-suggestion-title").sendKeys("newTitle");
        $("#new-suggestion-content").sendKeys("newSuggestionContent");
        $("#new-suggestion-submit").click();

        // Now there should be three Suggestions
        $$("[data-suggestion-display]").shouldHave(size(3));

        refresh();

        // Now there should be three Suggestions again after the refresh
        $$("[data-suggestion-display]").shouldHave(size(3));

        // Check that the data is showing up for the third Suggestion
        Long thirdSuggestionId = secondSuggestionId + 1;
        $("#suggestion-" + thirdSuggestionId + "-title").shouldHave(text("newTitle"));
        $("#suggestion-" + thirdSuggestionId + "-content").shouldHave(text("newSuggestionContent"));
        $("#suggestion-" + thirdSuggestionId + "-user-name").shouldHave(text("newUserName"));

        // Test Deleting the first suggestion
        $("#user-" + firstSuggestionId).should(exist);
        $$("[data-suggestion-display]").shouldHave(size(3));

        $("#delete-suggestion-" + firstSuggestionId).click();
        $("#suggestion-" + firstSuggestionId).shouldNot(exist);

        $$("[data-suggestion-display]").shouldHave(size(2));

        refresh();

        // Double check the suggestion was deleted
        $("#suggestion-" + firstSuggestionId).shouldNot(exist);
        $$("[data-suggestion-display]").shouldHave(size(2));

        // Leave the suggestions page and visit the admin view page
        $("#admin-view-link").click();

        // Make sure the link worked and the admin view page is now showing
        $("#users-wrapper").should(appear);
    }
}
