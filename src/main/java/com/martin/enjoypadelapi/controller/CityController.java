package com.martin.enjoypadelapi.controller;

import com.martin.enjoypadelapi.domain.City;
import com.martin.enjoypadelapi.exception.CityNotFoundException;
import com.martin.enjoypadelapi.exception.ErrorResponse;
import com.martin.enjoypadelapi.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CityController {

    private final Logger logger = LoggerFactory.getLogger(PlayerController.class);

    @Autowired
    private CityService cityService;

    @GetMapping("/city")
    public List<City> findAll() {
        logger.info("Inicio findAll(city)");
        List<City> cities = cityService.findAll();
        logger.info("Final findAll(city)");
        return cities;
    }

    @GetMapping("/city/{id}")
    public City findById(@PathVariable long id) throws CityNotFoundException {
        logger.info("Inicio findById(city)");
        City city = cityService.findById(id);
        logger.info("Final findById(city)");
        return city;
    }

    @PostMapping("/cities")
    public City addCity (@RequestBody City newCity) {
        logger.info("Inicio addCity");
        City city = cityService.addCity(newCity);
        logger.info("Final addCity");
        return city;
    }

    @PutMapping("/city/{id}")
    public City modifyCity(@PathVariable long id, @RequestBody City newCity) throws CityNotFoundException {
        logger.info("Inicio modifyCity");
        City city = cityService.modifyCity(id, newCity);
        logger.info("Final modifyCity");
        return city;
    }

    @DeleteMapping("/city/{id}")
    public City deleteCity (@PathVariable long id) throws CityNotFoundException {
        logger.info("Inicio deleteCity");
        City city = cityService.deleteCity(id);
        logger.info("Final deleteCity");
        return city;
    }

    @PatchMapping("/city/{id}")
    public City partialCityModification(@PathVariable long id, @RequestBody Map<Object, Object> fields) throws CityNotFoundException {
        logger.info("Inicio partialCityModification");
        City city = cityService.partialCityModification(id, fields);
        logger.info("Final partialCityModification");
        return city;
    }


    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCityNotFoundException(CityNotFoundException cinfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", cinfe.getMessage());
        logger.error(cinfe.getMessage(), cinfe);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
