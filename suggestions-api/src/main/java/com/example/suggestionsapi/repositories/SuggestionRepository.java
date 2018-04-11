package com.example.suggestionsapi.repositories;

import com.example.suggestionsapi.models.Suggestion;
import org.springframework.data.repository.CrudRepository;

public interface SuggestionRepository extends CrudRepository<Suggestion, Long> {

}
