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
    private UserRepository userRepository;

    @Autowired
    private SuggestionRepository suggestionRepository;

    @Before
    public void setUp() {
        userRepository.deleteAll();
        suggestionRepository.deleteAll();
    }

    @After
    public void tearDown() {
        userRepository.deleteAll();
        suggestionRepository.deleteAll();
    }

    @Test
    public void shouldAllowFullCrudFunctionalityForAUser() throws Exception {

        User firstUser = new User(
                "firstUser@email.com",
                "user1First",
                "user1Last",
                "password1"
        );
        firstUser = userRepository.save(firstUser);
        Long firstUserId = firstUser.getId();

        User secondUser = new User(
                "secondUser@email.com",
                "user2First",
                "user2Last",
                "password2"
        );
        secondUser = userRepository.save(secondUser);
        Long secondUserId = secondUser.getId();

        Suggestion firstSuggestion = new Suggestion(
                "title1",
                "suggestionContent1",
                2L
        );
        firstSuggestion = suggestionRepository.save(firstSuggestion);
        Long firstSuggestionId = firstSuggestion.getId();

        Suggestion secondSuggestion = new Suggestion(
                "title2",
                "suggestionContent2",
                1L
        );
        secondSuggestion = suggestionRepository.save(secondSuggestion);
        Long secondSuggestionId = secondSuggestion.getId();

        System.setProperty("selenide.browser", "Chrome");
    }
}
