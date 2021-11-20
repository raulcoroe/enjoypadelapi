package com.example.enjoypadelapi.controller;

import com.example.enjoypadelapi.domain.Team;
import com.example.enjoypadelapi.domain.User;
import com.example.enjoypadelapi.exception.TeamNotFoundException;
import com.example.enjoypadelapi.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class TeamController {

    @Autowired
    TeamService teamService;

    @GetMapping("/teams")
    public List<Team> findAll() {
        List<Team> teams = teamService.findAll();
        return teams;
    }

    @GetMapping("/team/{id}")
    public Team findById (@PathVariable long id) throws TeamNotFoundException {
        Team team = teamService.findById(id);
        return team;
    }

    @PostMapping("/teams")
    public Team addTeam(@RequestBody Team newTeam){
        Team team = teamService.addTeam(newTeam);
        return team;
    }

    @DeleteMapping("/team/{id}")
    public Team deleteTeam(@PathVariable long id) throws TeamNotFoundException {
        Team team = teamService.deleteTeam(id);
        return team;
    }

    @GetMapping("/teamusers/{id}")
    public List<User> findTeamUsers(@PathVariable long id) throws TeamNotFoundException {
        List<User> users = teamService.findTeamUsers(id);
        return users;
    }

}
