package com.example.suggestionsapi.controllers;

import com.example.suggestionsapi.models.Suggestion;
import com.example.suggestionsapi.repositories.SuggestionRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class SuggestionsController {

    @Autowired
    private SuggestionRepository suggestionRepository;

    @GetMapping("/")
    public Iterable<Suggestion> findAllSuggestions() {
        return suggestionRepository.findAll();
    }

    @GetMapping("/{suggestionId}")
    public Suggestion findSuggestionById(@PathVariable Long suggestionId) throws NotFoundException {

        Suggestion foundSuggestion = suggestionRepository.findOne(suggestionId);

        if (foundSuggestion == null) {
            throw new NotFoundException("Suggestion with ID of " + suggestionId + " was not found!");
        }

        return foundSuggestion;
    }

    // EXCEPTION HANDLERS

    @ExceptionHandler
    void handleSuggestionNotFound(
            NotFoundException exception,
            HttpServletResponse response) throws IOException {

        response.sendError(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }
}
