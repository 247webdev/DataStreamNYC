package com.example.DataStreamNYC.features;

import com.example.DataStreamNYC.repositories.SuggestionRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
}
