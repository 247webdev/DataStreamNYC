package com.example.DataStreamNYC.features;

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
}
