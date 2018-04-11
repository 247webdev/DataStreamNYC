package com.example.suggestionsapi.controllers;

import com.example.suggestionsapi.models.Suggestion;
import com.example.suggestionsapi.repositories.SuggestionRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/{suggestionId}")
    public HttpStatus deleteSuggestionById(@PathVariable Long suggestionId) throws EmptyResultDataAccessException {

        suggestionRepository.delete(suggestionId);
        return HttpStatus.OK;
    }

    @PostMapping("/")
    public Suggestion createNewSuggestion(@RequestBody Suggestion newSuggestion) {
        return suggestionRepository.save(newSuggestion);
    }

    @PatchMapping("/{suggestionId}")
    public Suggestion updateSuggestionById(@PathVariable Long suggestionId, @RequestBody Suggestion suggestionRequest) {
        Suggestion suggestionFromDb = suggestionRepository.findOne(suggestionId);

        suggestionFromDb.setTitle(suggestionRequest.getTitle());
        suggestionFromDb.setContent(suggestionRequest.getContent());
        suggestionFromDb.setUserId(suggestionRequest.getUserId());

        return suggestionRepository.save(suggestionFromDb);
    }

    // EXCEPTION HANDLERS

    @ExceptionHandler
    void handleSuggestionNotFound(
            NotFoundException exception,
            HttpServletResponse response) throws IOException {

        response.sendError(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    @ExceptionHandler
    void handleDeleteNotFoundException(
            EmptyResultDataAccessException exception,
            HttpServletResponse response) throws IOException {

        response.sendError(HttpStatus.NOT_FOUND.value());
    }
}
