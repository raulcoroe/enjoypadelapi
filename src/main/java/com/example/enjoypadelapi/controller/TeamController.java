package com.example.enjoypadelapi.controller;

import com.example.enjoypadelapi.domain.Match;
import com.example.enjoypadelapi.domain.Team;
import com.example.enjoypadelapi.domain.Player;
import com.example.enjoypadelapi.exception.*;
import com.example.enjoypadelapi.service.MatchService;
import com.example.enjoypadelapi.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class TeamController {

    @Autowired
    private TeamService teamService;

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
    public Team deleteTeam(@PathVariable long id) throws TeamNotFoundException, PlayerNotFoundException {
        Team team = teamService.deleteTeam(id);
        return team;
    }

    @PutMapping("/team/{id}")
    public Team modifyTeam(@PathVariable long id, @RequestBody Team newTeam) throws TeamNotFoundException{
        Team team = teamService.modifyTeam(id, newTeam);
        return team;
    }

    @PatchMapping("/team/{id}")
    public Team partialTeamModification (@PathVariable long id, @RequestBody Map<Object, Object> fields) throws TeamNotFoundException {
        Team team = teamService.partialTeamModification(id, fields);
        return team;
    }

    @GetMapping("/team/{id}/players")
    public List<Player> listTeamPlayers(@PathVariable long id) throws TeamNotFoundException {
        List<Player> players = teamService.listTeamPlayers(id);
        return players;
    }

    @PostMapping("match/{match_id}/team/{team_id}")
    public Team addTeamToMatch (@PathVariable long match_id, @PathVariable long team_id) throws TeamNotFoundException, MatchNotFoundException, FullMatchException {
        Team team = teamService.addTeamToMatch(match_id, team_id);
        return team;
    }

    @DeleteMapping("/match/{match_id}/team/{team_id}")
    public Match deleteTeamToMatch(@PathVariable long match_id, @PathVariable long team_id) throws TeamNotFoundException, MatchNotFoundException {
        Match match = teamService.deletePlayerToTeam(match_id, team_id);
        return match;
    }

    @GetMapping("/team/{id}/matches")
    public List<Match> listTeamMatches (@PathVariable long id) throws TeamNotFoundException {
        List<Match> matches = teamService.listTeamMatches(id);
        return matches;
    }


    @ExceptionHandler(TeamNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTeamNotFoundException(TeamNotFoundException tnfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", tnfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MatchNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMatchNotFoundException(MatchNotFoundException mnfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", mnfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FullMatchException.class)
    public ResponseEntity<ErrorResponse> handleFullMatchException (FullMatchException fme) {
        ErrorResponse errorResponse = new ErrorResponse("400", fme.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
