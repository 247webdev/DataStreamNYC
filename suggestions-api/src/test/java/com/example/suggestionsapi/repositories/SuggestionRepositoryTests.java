package com.example.suggestionsapi.repositories;

import com.example.suggestionsapi.models.Suggestion;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SuggestionRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SuggestionRepository suggestionRepository;

    @Before
    public void setUp() {
        Suggestion firstSuggestion = new Suggestion(
                "title1",
                "contentSuggestion1",
                3L
        );

        Suggestion secondSuggestion = new Suggestion(
                "title2",
                "contentSuggestion2",
                5L
        );

        entityManager.persist(firstSuggestion);
        entityManager.persist(secondSuggestion);
        entityManager.flush();
    }
}
