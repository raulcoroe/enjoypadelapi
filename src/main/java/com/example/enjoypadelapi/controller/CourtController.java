package com.example.enjoypadelapi.controller;

import com.example.enjoypadelapi.domain.Court;
import com.example.enjoypadelapi.domain.Player;
import com.example.enjoypadelapi.domain.Team;
import com.example.enjoypadelapi.domain.dto.CourtDTO;
import com.example.enjoypadelapi.exception.*;
import com.example.enjoypadelapi.service.CourtService;
import com.example.enjoypadelapi.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CourtController {

    @Autowired
    private CourtService courtService;

    @GetMapping("/courts")
    public List<Court> findAll() {
        List<Court> courts = courtService.findAll();
        return courts;
    }

    @GetMapping("/court/{id}")
    public Court findById(@PathVariable long id) throws CourtNotFoundException {
        Court court = courtService.findById(id);
        return court;
    }

    @PostMapping("/courts")
    public Court addCourt(@RequestBody CourtDTO courtDto) throws CenterNotFoundException {
        Court court = courtService.addCourt(courtDto);
        return court;
    }

    @PutMapping("/court/{id}")
    public Court modifyCourt(@PathVariable long id, @RequestBody CourtDTO courtDto) throws CourtNotFoundException, CenterNotFoundException {
        Court court = courtService.modifyCourt(id, courtDto);
        return court;
    }

    @DeleteMapping("/court/{id}")
    public Court deleteCourt (@PathVariable long id) throws CourtNotFoundException {
        Court court = courtService.deleteCourt(id);
        return court;
    }

    @PatchMapping("/court/{id}")
    public Court partialCourtModification(@PathVariable long id, @RequestBody Map<Object, Object> fields) throws CourtNotFoundException {
        Court court = courtService.partialCourtModification(id, fields);
        return court;
    }


    @ExceptionHandler(CourtNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCourtNotFoundException(CourtNotFoundException cnfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", cnfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CenterNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCenterNotFoundException(CenterNotFoundException cenfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", cenfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
