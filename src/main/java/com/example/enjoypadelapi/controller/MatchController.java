package com.example.enjoypadelapi.controller;

import com.example.enjoypadelapi.domain.Match;
import com.example.enjoypadelapi.domain.Player;
import com.example.enjoypadelapi.domain.Team;
import com.example.enjoypadelapi.domain.dto.MatchDTO;
import com.example.enjoypadelapi.exception.*;
import com.example.enjoypadelapi.service.MatchService;
import com.example.enjoypadelapi.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.MarshalException;
import java.util.List;
import java.util.Map;

@RestController
public class MatchController {

    @Autowired
    private MatchService matchService;

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
    public Match addMatch(@RequestBody MatchDTO matchDto) throws CourtNotFoundException {
        Match match = matchService.addMatch(matchDto);
        return match;
    }

    @PutMapping("/match/{id}")
    public Match modifyMatch (@PathVariable long id, @RequestBody Match newMatch) throws MatchNotFoundException{
        Match match = matchService.modifyMatch(id, newMatch);
        return match;
    }

    @PatchMapping("/match/{id}")
    public Match partialMatchModification(@PathVariable long id, @RequestBody Map<Object, Object> fields) throws MatchNotFoundException {
        Match match = matchService.partialMatchModification(id, fields);
        return match;
    }

    @DeleteMapping("/match/{id}")
    public Match deleteMatch(@PathVariable long id) throws MatchNotFoundException {
        Match match = matchService.deleteMatch(id);
        return match;
    }

    @GetMapping("/match/{id}/teams")
    public List<Team> listMatchTeams(@PathVariable long id) throws MatchNotFoundException {
        List<Team> teams = matchService.listMatchTeams(id);
        return teams;
    }

    @ExceptionHandler(MatchNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMatchNotFoundException(MatchNotFoundException mnfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", mnfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
