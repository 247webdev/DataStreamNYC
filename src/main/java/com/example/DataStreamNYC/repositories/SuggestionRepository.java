package com.example.DataStreamNYC.repositories;

import com.example.DataStreamNYC.models.Suggestion;
import org.springframework.data.repository.CrudRepository;

public interface SuggestionRepository extends CrudRepository<Suggestion, Long> {

}
