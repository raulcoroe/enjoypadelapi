package com.martin.enjoypadelapi.controller;

import com.martin.enjoypadelapi.domain.Match;
import com.martin.enjoypadelapi.domain.Team;
import com.martin.enjoypadelapi.domain.dto.MatchDTO;
import com.martin.enjoypadelapi.exception.*;
import com.martin.enjoypadelapi.exception.ErrorResponse;
import com.martin.enjoypadelapi.exception.MatchNotFoundException;
import com.martin.enjoypadelapi.service.MatchService;
import com.martin.enjoypadelapi.exception.CourtNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class MatchController {

    private final Logger logger = LoggerFactory.getLogger(PlayerController.class);

    @Autowired
    private MatchService matchService;

    @GetMapping("/matches")
    public List<Match> findAll() {
        logger.info("Inicio findAll(matches)");
        List<Match> matches = matchService.findAll();
        logger.info("Final findAll(matches)");
        return matches;
    }

    @GetMapping("/match/{id}")
    public Match findById(@PathVariable long id) throws MatchNotFoundException {
        logger.info("Inicio findById(match(");
        Match match = matchService.findById(id);
        logger.info("Final findById(match)");
        return match;
    }

    @PostMapping("/matches")
    public Match addMatch(@RequestBody MatchDTO matchDto) throws CourtNotFoundException {
        logger.info("Inicio addMatch");
        Match match = matchService.addMatch(matchDto);
        logger.info("Final addMatch");
        return match;
    }

    @PutMapping("/match/{id}")
    public Match modifyMatch(@PathVariable long id, @RequestBody MatchDTO matchDto) throws MatchNotFoundException, CourtNotFoundException {
        logger.info("Inicio modifyMatch");
        Match match = matchService.modifyMatch(id, matchDto);
        logger.info("Final modifyMatch");
        return match;
    }

    @PatchMapping("/match/{id}")
    public Match partialMatchModification(@PathVariable long id, @RequestBody Map<Object, Object> fields) throws MatchNotFoundException {
        logger.info("Inicio partialMatchModification");
        Match match = matchService.partialMatchModification(id, fields);
        logger.info("Final partialMatchModification");
        return match;
    }

    @DeleteMapping("/match/{id}")
    public Match deleteMatch(@PathVariable long id)throws MatchNotFoundException {
        logger.info("Inicio deleteMatch");
        Match match = matchService.deleteMatch(id);
        logger.info("Final deleteMatch");
        return match;
    }

    @GetMapping("/match/{id}/teams")
    public List<Team> listMatchTeams(@PathVariable long id) throws MatchNotFoundException {
        logger.info("Inicio listMatchTeams");
        List<Team> teams = matchService.listMatchTeams(id);
        logger.info("Final listMatchTeams");
        return teams;
    }

    @ExceptionHandler(MatchNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMatchNotFoundException(MatchNotFoundException mnfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", mnfe.getMessage());
        logger.error(mnfe.getMessage(), mnfe);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CourtNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCourtNotFoundException(CourtNotFoundException cnfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", cnfe.getMessage());
        logger.error(cnfe.getMessage(), cnfe);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
