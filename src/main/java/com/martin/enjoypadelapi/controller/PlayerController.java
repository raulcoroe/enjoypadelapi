package com.martin.enjoypadelapi.controller;

import com.martin.enjoypadelapi.domain.Player;
import com.martin.enjoypadelapi.domain.Team;
import com.martin.enjoypadelapi.exception.ErrorResponse;
import com.martin.enjoypadelapi.exception.FullTeamException;
import com.martin.enjoypadelapi.exception.TeamNotFoundException;
import com.martin.enjoypadelapi.exception.PlayerNotFoundException;
import com.martin.enjoypadelapi.service.PlayerService;
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
        logger.info("Final getPlayers");
        return players;
    }


    @GetMapping("/player/{id}")
    public Player findById(@PathVariable long id) throws PlayerNotFoundException {
        logger.info("Inicio findById");
        Player player = playerService.findById(id);
        logger.info("Final findById");
        return player;
    }

    @PostMapping("/players")
    public Player addPlayer(@RequestBody Player newPlayer) {
        logger.info("Inicio addPlayer");
        Player player = playerService.addPlayer(newPlayer);
        logger.info("Final addPlayer");
        return player;
    }

    @PutMapping("/player/{id}")
    public Player modifyPlayer(@PathVariable long id, @RequestBody Player newPlayer) throws PlayerNotFoundException {
        logger.info("Inicio modifyPlayer");
        Player player = playerService.modifyPlayer(id, newPlayer);
        logger.info("Final modifyPlayer");
        return player;
    }

    @DeleteMapping("/player/{id}")
    public Player deletePlayer(@PathVariable long id) throws PlayerNotFoundException {
        logger.info("Inicio deletePlayer");
        Player player = playerService.deletePlayer(id);
        logger.info("Final deletePlayer");
        return player;
    }

    @PatchMapping("/player/{id}")
    public Player partialPlayerModification(@PathVariable long id, @RequestBody Map<Object, Object> fields) throws PlayerNotFoundException {
        logger.info("Inicio partialPlayerModification");
        Player player = playerService.partialPlayerModification(id, fields);
        logger.info("Final partialPlayerModification");
        return player;
    }

    @PostMapping("/team/{team_id}/player/{player_id}")
    public Team addPlayerToTeam(@PathVariable long team_id, @PathVariable long player_id) throws PlayerNotFoundException, TeamNotFoundException, FullTeamException {
        logger.info("Inicio addPlayerToTeam");
        Team team = playerService.addPlayerToTeam(player_id, team_id);
        logger.info("Final addPLayerToTeam");
        return team;
    }

    @GetMapping("player/{id}/teams")
    public List<Team> listPlayerTeams(@PathVariable long id) throws PlayerNotFoundException {
        logger.info("Inicio listPlayerTeams");
        List<Team> teams = playerService.listPlayerTeams(id);
        logger.info("final listPlayerTeams");
        return teams;
    }

    @DeleteMapping("/team/{team_id}/player/{player_id}")
    public Team deletePlayerToTeam(@PathVariable long team_id, @PathVariable long player_id) throws PlayerNotFoundException, TeamNotFoundException {
        logger.info("Inicio deletePlayerToTeam");
        Team team = playerService.deletePlayerToTeam(player_id, team_id);
        logger.info("Final deletePlayerToTeam");
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
        logger.error(fte.getMessage(), fte);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TeamNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTeamNotFoundException(TeamNotFoundException tnfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", tnfe.getMessage());
        logger.error(tnfe.getMessage(), tnfe);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
