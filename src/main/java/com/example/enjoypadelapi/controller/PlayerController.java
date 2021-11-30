package com.example.enjoypadelapi.controller;

import com.example.enjoypadelapi.domain.Player;
import com.example.enjoypadelapi.domain.Team;
import com.example.enjoypadelapi.exception.ErrorResponse;
import com.example.enjoypadelapi.exception.FullTeamException;
import com.example.enjoypadelapi.exception.TeamNotFoundException;
import com.example.enjoypadelapi.exception.PlayerNotFoundException;
import com.example.enjoypadelapi.service.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class PlayerController {

    private final Logger logger = LoggerFactory.getLogger(PlayerController.class);

    @Autowired
    private PlayerService playerService;

    @GetMapping("/players")
    public List<Player> findAll() {
        logger.info("Inicio getPlayers");
        List<Player> players = playerService.findAll();
        return players;
    }


    @GetMapping("/player/{id}")
    public Player findById(@PathVariable long id) throws PlayerNotFoundException {
        Player player = playerService.findById(id);
        return player;
    }

    @PostMapping("/players")
    public Player addPlayer(@RequestBody Player newPlayer) {
        Player player = playerService.addPlayer(newPlayer);
        return player;
    }

    @PutMapping("/player/{id}")
    public Player modifyPlayer(@PathVariable long id, @RequestBody Player newPlayer) throws PlayerNotFoundException {
        Player player = playerService.modifyPlayer(id, newPlayer);
        return player;
    }

    @DeleteMapping("/player/{id}")
    public Player deletePlayer(@PathVariable long id) throws PlayerNotFoundException {
        Player player = playerService.deletePlayer(id);
        return player;
    }

    @PatchMapping("/player/{id}")
    public Player partialPlayerModification(@PathVariable long id, @RequestBody Map<Object, Object> fields) throws PlayerNotFoundException {
        Player player = playerService.partialPlayerModification(id, fields);
        return player;
    }

    @PostMapping("/team/{team_id}/player/{player_id}")
    public Team addPlayerToTeam(@PathVariable long team_id, @PathVariable long player_id) throws PlayerNotFoundException, TeamNotFoundException, FullTeamException {
        Team team = playerService.addPlayerToTeam(player_id, team_id);
        return team;
    }

    @GetMapping("player/{id}/teams")
    public List<Team> listPlayerTeams(@PathVariable long id) throws PlayerNotFoundException {
        List<Team> teams = playerService.listPlayerTeams(id);
        return teams;
    }

    @DeleteMapping("/team/{team_id}/player/{player_id}")
    public Team deletePlayerToTeam(@PathVariable long team_id, @PathVariable long player_id) throws PlayerNotFoundException, TeamNotFoundException {
        Team team = playerService.deletePlayerToTeam(player_id, team_id);
        return team;
    }


    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePlayerNotFoundException(PlayerNotFoundException pnfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", pnfe.getMessage());
        logger.error(pnfe.getMessage(), pnfe);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FullTeamException.class)
    public ResponseEntity<ErrorResponse> handleFullTeamException (FullTeamException fte) {
        ErrorResponse errorResponse = new ErrorResponse("400", fte.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TeamNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTeamNotFoundException(TeamNotFoundException tnfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", tnfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
