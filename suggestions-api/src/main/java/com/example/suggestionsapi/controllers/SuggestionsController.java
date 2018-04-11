package com.example.suggestionsapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SuggestionsController {

    @GetMapping("/")
    public String findAllSuggestions() {
        return "It's working!!!";
    }
}
