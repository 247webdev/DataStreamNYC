package com.example.suggestionsapi.controllers;

import com.example.suggestionsapi.models.Suggestion;
import com.example.suggestionsapi.repositories.SuggestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SuggestionsController {

    @Autowired
    private SuggestionRepository suggestionRepository;

    @GetMapping("/")
    public Iterable<Suggestion> findAllSuggestions() {
        return suggestionRepository.findAll();
    }
}
