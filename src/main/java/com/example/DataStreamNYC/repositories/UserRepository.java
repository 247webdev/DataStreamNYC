package com.example.DataStreamNYC.repositories;

import com.example.DataStreamNYC.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}