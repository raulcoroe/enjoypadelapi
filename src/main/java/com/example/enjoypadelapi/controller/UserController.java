package com.example.enjoypadelapi.controller;

import com.example.enjoypadelapi.domain.Team;
import com.example.enjoypadelapi.domain.User;
import com.example.enjoypadelapi.exception.TeamNotFoundException;
import com.example.enjoypadelapi.exception.UserNotFoundException;
import com.example.enjoypadelapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> findAll() {
        List<User> users = userService.findAll();
        return users;
    }

    @GetMapping("/user/{id}")
    public User findById (@PathVariable long id) throws UserNotFoundException {
        User user = userService.findById(id);
        return user;
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User newUser){
        User user = userService.addUser(newUser);
        return user;
    }

    @DeleteMapping("/user/{id}")
    public User deleteUser(@PathVariable long id) throws UserNotFoundException {
        User user = userService.deleteUser(id);
        return user;
    }

    @PostMapping("/addusertoteam/{user_id}/{team_id}")
    public Team addUserToTeam(@PathVariable long user_id, @PathVariable long team_id) throws UserNotFoundException, TeamNotFoundException {
        Team team = userService.addUserToTeam(user_id, team_id);
        return team;
    }

    @GetMapping("userteams/{id}")
    public List<Team> findUserTeams(@PathVariable long id) throws UserNotFoundException{
        List<Team> teams = userService.findUserTeams(id);
        return teams;
    }

    @DeleteMapping("/deleteusertoteam/{user_id}/{team_id}")
    public Team deleteUserToTeam(@PathVariable long user_id, @PathVariable long team_id) throws UserNotFoundException, TeamNotFoundException {
        Team team = userService.deleteUserToTeam(user_id, team_id);
        return team;
    }
}
