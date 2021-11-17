package com.example.enjoypadelapi.controller;

import com.example.enjoypadelapi.domain.Match;
import com.example.enjoypadelapi.domain.Team;
import com.example.enjoypadelapi.exception.MatchNotFoundException;
import com.example.enjoypadelapi.exception.TeamNotFoundException;
import com.example.enjoypadelapi.service.MatchService;
import com.example.enjoypadelapi.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class MatchController {

    @Autowired
    MatchService matchService;

    @GetMapping("/matches")
    List<Match> findAll() {
        List<Match> matches = matchService.findAll();
        return matches;
    }

    @GetMapping("/match/{id}")
    Match findById (@PathVariable long id) throws MatchNotFoundException {
        Match match = matchService.findById(id);
        return match;
    }

    @PostMapping("/matches")
    Match addTeam(@RequestBody Match newMatch){
        Match match = matchService.addMatch(newMatch);
        return match;
    }

    @DeleteMapping("/match/{id}")
    Match deleteMatch(@PathVariable long id){
        Match match = matchService.deleteMatch(id);
        return match;
    }
}
