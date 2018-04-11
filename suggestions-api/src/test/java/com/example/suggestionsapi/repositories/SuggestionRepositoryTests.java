package com.example.suggestionsapi.repositories;

import com.example.suggestionsapi.models.Suggestion;
import com.google.common.collect.Iterables;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

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

    @Test
    public void findAll_returnsAllSuggestions() {
        Iterable<Suggestion> suggestionsFromDb = suggestionRepository.findAll();

        assertThat(Iterables.size(suggestionsFromDb), is(2));
    }

    @Test
    public void findAll_returnsTitle() {
        Iterable<Suggestion> suggestionsFromDb = suggestionRepository.findAll();

        String secondSuggestionsSuggestionName = Iterables.get(suggestionsFromDb, 1).getTitle();

        assertThat(secondSuggestionsSuggestionName, is("title2"));
    }

    @Test
    public void findAll_returnsContent() {
        Iterable<Suggestion> suggestionsFromDb = suggestionRepository.findAll();

        String secondSuggestionsFirstName = Iterables.get(suggestionsFromDb, 1).getContent();

        assertThat(secondSuggestionsFirstName, is("contentSuggestion2"));
    }

    @Test
    public void findAll_returnsUserId() {
        Iterable<Suggestion> suggestionsFromDb = suggestionRepository.findAll();

        Long secondSuggestionsLastName = Iterables.get(suggestionsFromDb, 1).getUserId();

        assertThat(secondSuggestionsLastName, is(5L));
    }
}
