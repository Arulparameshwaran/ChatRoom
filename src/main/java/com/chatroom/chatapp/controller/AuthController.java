package com.chatroom.chatapp.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import com.chatroom.chatapp.model.User;
import com.chatroom.chatapp.repository.UserRepository;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    private final UserRepository repository;

    public AuthController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {

        if (repository.findByUsername(user.getUsername()).isPresent()) {
            return "Username already exists";
        }

        repository.save(user);
        return "Registered successfully";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {

        Optional<User> existing =
                repository.findByUsername(user.getUsername());

        if (existing.isPresent()
                && existing.get().getPassword().equals(user.getPassword())) {
            return "SUCCESS";
        }

        return "INVALID";
    }
}
