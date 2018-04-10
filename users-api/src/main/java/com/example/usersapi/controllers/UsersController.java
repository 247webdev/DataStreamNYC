package com.example.usersapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {

    @GetMapping("/")
    public String findAllUsers() {
        return "It's working!!!";
    }

}
