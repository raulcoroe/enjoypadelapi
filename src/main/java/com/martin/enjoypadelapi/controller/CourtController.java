package com.martin.enjoypadelapi.controller;

import com.martin.enjoypadelapi.domain.Court;
import com.martin.enjoypadelapi.domain.dto.CourtDTO;
import com.martin.enjoypadelapi.exception.CenterNotFoundException;
import com.martin.enjoypadelapi.exception.CourtNotFoundException;
import com.martin.enjoypadelapi.exception.ErrorResponse;
import com.martin.enjoypadelapi.service.CourtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
public class CourtController {

    private final Logger logger = LoggerFactory.getLogger(PlayerController.class);

    @Autowired
    private CourtService courtService;

    @GetMapping("/courts")
    public List<Court> findAll() {
        logger.info("Inicio findAll(courts)");
        List<Court> courts = courtService.findAll();
        logger.info("Final findAll(courts)");
        return courts;
    }

    @GetMapping("/court/{id}")
    public Court findById(@PathVariable long id) throws CourtNotFoundException {
        logger.info("Inicio findById(court)");
        Court court = courtService.findById(id);
        logger.info("Final findById(court");
        return court;
    }

    @PostMapping("/courts")
    public Court addCourt(@RequestBody CourtDTO courtDto) throws CenterNotFoundException {
        logger.info("Inicio addCourt");
        Court court = courtService.addCourt(courtDto);
        logger.info("Final addCourt");
        return court;
    }

    @PutMapping("/court/{id}")
    public Court modifyCourt(@PathVariable long id, @RequestBody CourtDTO courtDto) throws CourtNotFoundException, CenterNotFoundException {
        logger.info("Inicio modifyCourt");
        Court court = courtService.modifyCourt(id, courtDto);
        logger.info("Final modifyCourt");
        return court;
    }

    @DeleteMapping("/court/{id}")
    public Court deleteCourt (@PathVariable long id) throws CourtNotFoundException {
        logger.info("Inicio deleteCourt");
        Court court = courtService.deleteCourt(id);
        logger.info("Final deleteCourt");
        return court;
    }

    @PatchMapping("/court/{id}")
    public Court partialCourtModification(@PathVariable long id, @RequestBody Map<Object, Object> fields) throws CourtNotFoundException {
        logger.info("Inicio partialCourtModification");
        Court court = courtService.partialCourtModification(id, fields);
        logger.info("Final partialCourtModification");
        return court;
    }

    @GetMapping("/lighting/courts")
    public List<Court> findLightingCourts (@RequestParam boolean lighting) {
        logger.info("Inicio findLighting courts");
        List<Court> courts = courtService.findLightingCourts(lighting);
        logger.info("Final findLighting courts");
        return courts;
    }


    @ExceptionHandler(CourtNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCourtNotFoundException(CourtNotFoundException cnfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", cnfe.getMessage());
        logger.error(cnfe.getMessage(), cnfe);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CenterNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCenterNotFoundException(CenterNotFoundException cenfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", cenfe.getMessage());
        logger.error(cenfe.getMessage(), cenfe);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
