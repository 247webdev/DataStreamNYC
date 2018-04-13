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
                "user3"
        );

        Suggestion secondSuggestion = new Suggestion(
                "title2",
                "contentSuggestion2",
                "user5"
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

        String secondSuggestionsTitle = Iterables.get(suggestionsFromDb, 1).getTitle();

        assertThat(secondSuggestionsTitle, is("title2"));
    }

    @Test
    public void findAll_returnsContent() {
        Iterable<Suggestion> suggestionsFromDb = suggestionRepository.findAll();

        String secondSuggestionsContent = Iterables.get(suggestionsFromDb, 1).getContent();

        assertThat(secondSuggestionsContent, is("contentSuggestion2"));
    }

    @Test
    public void findAll_returnsUserId() {
        Iterable<Suggestion> suggestionsFromDb = suggestionRepository.findAll();

        String secondSuggestionsUserName = Iterables.get(suggestionsFromDb, 1).getUserName();

        assertThat(secondSuggestionsUserName, is("user5"));
    }
}
