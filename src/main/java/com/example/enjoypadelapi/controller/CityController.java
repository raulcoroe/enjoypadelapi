package com.example.enjoypadelapi.controller;

import com.example.enjoypadelapi.domain.City;
import com.example.enjoypadelapi.exception.CityNotFoundException;
import com.example.enjoypadelapi.exception.CourtNotFoundException;
import com.example.enjoypadelapi.exception.ErrorResponse;
import com.example.enjoypadelapi.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("/city")
    public List<City> findAll() {
        List<City> cities = cityService.findAll();
        return cities;
    }

    @GetMapping("/city/{id}")
    public City findById(@PathVariable long id) throws CityNotFoundException {
        City city = cityService.findById(id);
        return city;
    }

    @PostMapping("/cities")
    public City addCity (@RequestBody City newCity) {
        City city = cityService.addCity(newCity);
        return city;
    }

    @PutMapping("/city/{id}")
    public City modifyCity(@PathVariable long id, @RequestBody City newCity) throws CityNotFoundException {
        City city = cityService.modifyCity(id, newCity);
        return city;
    }

    @DeleteMapping("/city/{id}")
    public City deleteCity (@PathVariable long id) throws CityNotFoundException {
        City city = cityService.deleteCity(id);
        return city;
    }

    @PatchMapping("/city/{id}")
    public City partialCityModification(@PathVariable long id, @RequestBody Map<Object, Object> fields) throws CityNotFoundException {
        City city = cityService.partialCityModification(id, fields);
        return city;
    }


    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCityNotFoundException(CityNotFoundException cinfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", cinfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
