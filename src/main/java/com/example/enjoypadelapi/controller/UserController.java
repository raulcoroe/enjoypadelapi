package com.example.enjoypadelapi.controller;

import com.example.enjoypadelapi.domain.User;
import com.example.enjoypadelapi.exception.UserNotFoundException;
import com.example.enjoypadelapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    List<User> findAll() {
        List<User> users = userService.findAll();
        return users;
    }

    @GetMapping("/user/{id}")
    User findById (@PathVariable long id) throws UserNotFoundException {
        User user = userService.findById(id);
        return user;
    }

    @PostMapping("/users")
    User addUser(@RequestBody User newUser){
        User user = userService.addUser(newUser);
        return user;
    }

    @DeleteMapping("/user/{id}")
    User deleteUser(@PathVariable long id) throws UserNotFoundException {
        User user = userService.deleteUser(id);
        return user;
    }

}
