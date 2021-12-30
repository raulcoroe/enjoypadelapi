package com.martin.enjoypadelapi.controller;

import com.martin.enjoypadelapi.domain.Match;
import com.martin.enjoypadelapi.domain.Team;
import com.martin.enjoypadelapi.domain.Player;
import com.martin.enjoypadelapi.exception.*;
import com.martin.enjoypadelapi.service.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class TeamController {

    private final Logger logger = LoggerFactory.getLogger(TeamController.class);

    @Autowired
    private TeamService teamService;

    @GetMapping("/teams")
    public List<Team> findAll() {
        logger.info("Inicio findAllTeams");
        List<Team> teams = teamService.findAll();
        logger.info("Final findAllTeams");
        return teams;
    }

    @GetMapping("/team/{id}")
    public Team findById (@PathVariable long id) throws TeamNotFoundException {
        logger.info("Inicio findById(team)");
        Team team = teamService.findById(id);
        logger.info("Final findById(team");
        return team;
    }

    @PostMapping("/teams")
    public Team addTeam(@RequestBody Team newTeam){
        logger.info("Inicio addTeam");
        Team team = teamService.addTeam(newTeam);
        logger.info("Final addTeam");
        return team;
    }

    @DeleteMapping("/team/{id}")
    public Team deleteTeam(@PathVariable long id) throws TeamNotFoundException, PlayerNotFoundException {
        logger.info("Inicio deleteTeam");
        Team team = teamService.deleteTeam(id);
        logger.info("Final deleteTeam");
        return team;
    }

    @PutMapping("/team/{id}")
    public Team modifyTeam(@PathVariable long id, @RequestBody Team newTeam) throws TeamNotFoundException{
        logger.info("Inicio modifyTeam");
        Team team = teamService.modifyTeam(id, newTeam);
        logger.info("Final modifyTeam");
        return team;
    }

    @PatchMapping("/team/{id}")
    public Team partialTeamModification (@PathVariable long id, @RequestBody Map<Object, Object> fields) throws TeamNotFoundException {
        logger.info("Inicio partialTeamModification");
        Team team = teamService.partialTeamModification(id, fields);
        logger.info("Final partialTeamModification");
        return team;
    }

    @GetMapping("/team/{id}/players")
    public List<Player> listTeamPlayers(@PathVariable long id) throws TeamNotFoundException {
        logger.info("Inicio listTeamPlayer");
        List<Player> players = teamService.listTeamPlayers(id);
        logger.info("Final listTeamPlayer");
        return players;
    }

    @PostMapping("match/{match_id}/team/{team_id}")
    public Team addTeamToMatch (@PathVariable long match_id, @PathVariable long team_id) throws TeamNotFoundException, MatchNotFoundException, FullMatchException {
        logger.info("Inicio addTeamToMatch");
        Team team = teamService.addTeamToMatch(match_id, team_id);
        logger.info("Final addTeamToMatch");
        return team;
    }

    @DeleteMapping("/match/{match_id}/team/{team_id}")
    public Match deleteTeamToMatch(@PathVariable long match_id, @PathVariable long team_id) throws TeamNotFoundException, MatchNotFoundException {
        logger.info("Inicio deleteTeamToMatch");
        Match match = teamService.deletePlayerToTeam(match_id, team_id);
        logger.info("Final deleteTeamToMatch");
        return match;
    }

    @GetMapping("/team/{id}/matches")
    public List<Match> listTeamMatches (@PathVariable long id) throws TeamNotFoundException {
        logger.info("Inicio listTeamMatches");
        List<Match> matches = teamService.listTeamMatches(id);
        logger.info("Final listTeamMatches");
        return matches;
    }

    @GetMapping("/professional/teams")
    public List<Team> findProfessionalTeams (@RequestParam boolean professional) {
        logger.info("Inicio findProfessionalTeams");
        List<Team> teams = teamService.findProfessionalTeams(professional);
        logger.info("Final findProfessionalTeams");
        return teams;
    }


    @ExceptionHandler(TeamNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTeamNotFoundException(TeamNotFoundException tnfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", tnfe.getMessage());
        logger.error(tnfe.getMessage(), tnfe);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MatchNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMatchNotFoundException(MatchNotFoundException mnfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", mnfe.getMessage());
        logger.error(mnfe.getMessage(), mnfe);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FullMatchException.class)
    public ResponseEntity<ErrorResponse> handleFullMatchException (FullMatchException fme) {
        ErrorResponse errorResponse = new ErrorResponse("400", fme.getMessage());
        logger.error(fme.getMessage(), fme);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
