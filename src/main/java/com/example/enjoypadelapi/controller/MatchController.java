package com.example.enjoypadelapi.controller;

import com.example.enjoypadelapi.domain.Match;
import com.example.enjoypadelapi.domain.Team;
import com.example.enjoypadelapi.exception.ErrorResponse;
import com.example.enjoypadelapi.exception.MatchNotFoundException;
import com.example.enjoypadelapi.exception.PlayerNotFoundException;
import com.example.enjoypadelapi.exception.TeamNotFoundException;
import com.example.enjoypadelapi.service.MatchService;
import com.example.enjoypadelapi.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.MarshalException;
import java.util.List;
@RestController
public class MatchController {

    @Autowired
    MatchService matchService;

    @GetMapping("/matches")
    public List<Match> findAll() {
        List<Match> matches = matchService.findAll();
        return matches;
    }

    @GetMapping("/match/{id}")
    public Match findById (@PathVariable long id) throws MatchNotFoundException {
        Match match = matchService.findById(id);
        return match;
    }

    @PostMapping("/matches")
    public Match addTeam(@RequestBody Match newMatch){
        Match match = matchService.addMatch(newMatch);
        return match;
    }

    @PutMapping("/match/{id}")
    public Match modifyMatch (@PathVariable long id, @RequestBody Match newMatch) throws MatchNotFoundException{
        Match match = matchService.modifyMatch(id, newMatch);
        return match;
    }

    @DeleteMapping("/match/{id}")
    public Match deleteMatch(@PathVariable long id) throws MatchNotFoundException {
        Match match = matchService.deleteMatch(id);
        return match;
    }

    @ExceptionHandler(MatchNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMatchNotFoundException(MatchNotFoundException mnfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", mnfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
