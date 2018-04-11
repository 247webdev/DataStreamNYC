package com.example.suggestionsapi.controllers;

import com.example.suggestionsapi.models.Suggestion;
import com.example.suggestionsapi.repositories.SuggestionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SuggestionsController.class)
public class SuggestionsControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SuggestionRepository mockSuggestionRepository;

    @Before
    public void setUp() {
        Suggestion firstSuggestion = new Suggestion(
                "title1",
                "contentSuggestion1",
                2L
        );

        Suggestion secondSuggestion = new Suggestion(
                "title2",
                "contentSuggestion2",
                1L
        );

        Iterable<Suggestion> mockSuggestions =
                Stream.of(firstSuggestion, secondSuggestion).collect(Collectors.toList());

        given(mockSuggestionRepository.findAll()).willReturn(mockSuggestions);
        given(mockSuggestionRepository.findOne(1L)).willReturn(firstSuggestion);
        given(mockSuggestionRepository.findOne(4L)).willReturn(null);
    }

    @Test
    public void findAllSuggestions_success_returnsStatusOK() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void findAllSuggestions_success_returnAllSuggestionsAsJSON() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void findAllSuggestions_success_returnTitleForEachSuggestion() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(jsonPath("$[0].title", is("title1")));
    }

    @Test
    public void findAllSuggestions_success_returnContentForEachSuggestion() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(jsonPath("$[0].content", is("contentSuggestion1")));
    }

    @Test
    public void findAllSuggestions_success_returnUserIdForEachSuggestion() throws Exception {

        this.mockMvc
                .perform(get("/"))
                .andExpect(jsonPath("$[0].userId", is(2)));
    }

    @Test
    public void findSuggestionById_success_returnsStatusOK() throws Exception {

        this.mockMvc
                .perform(get("/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void findSuggestionById_success_returnTitle() throws Exception {

        this.mockMvc
                .perform(get("/1"))
                .andExpect(jsonPath("$.title", is("title1")));
    }

    @Test
    public void findSuggestionById_success_returnContent() throws Exception {

        this.mockMvc
                .perform(get("/1"))
                .andExpect(jsonPath("$.content", is("contentSuggestion1")));
    }

    @Test
    public void findSuggestionById_success_returnUserId() throws Exception {

        this.mockMvc
                .perform(get("/1"))
                .andExpect(jsonPath("$.userId", is(2)));
    }

    @Test
    public void findSuggestionById_failure_suggestionNotFoundReturns404() throws Exception {

        this.mockMvc
                .perform(get("/4"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void findSuggestionById_failure_suggestionNotFoundReturnsNotFoundErrorMessage() throws Exception {

        this.mockMvc
                .perform(get("/4"))
                .andExpect(status().reason(containsString("Suggestion with ID of 4 was not found!")));
    }

    @Test
    public void deleteSuggestionById_success_returnsStatusOk() throws Exception {

        this.mockMvc
                .perform(delete("/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteSuggestionById_success_deletesViaRepository() throws Exception {

        this.mockMvc.perform(delete("/1"));

        verify(mockSuggestionRepository, times(1)).delete(1L);
    }
}
