package com.example.enjoypadelapi.controller;

import com.example.enjoypadelapi.domain.Center;
import com.example.enjoypadelapi.domain.Court;
import com.example.enjoypadelapi.domain.dto.CenterDTO;
import com.example.enjoypadelapi.exception.CenterNotFoundException;
import com.example.enjoypadelapi.exception.CityNotFoundException;
import com.example.enjoypadelapi.exception.CourtNotFoundException;
import com.example.enjoypadelapi.exception.ErrorResponse;
import com.example.enjoypadelapi.service.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CenterController {

    @Autowired
    private CenterService centerService;

    @GetMapping("/centers")
    public List<Center> findAll() {
        List<Center> centers = centerService.findAll();
        return centers;
    }

    @GetMapping("/center/{id}")
    public Center findById(@PathVariable long id) throws CenterNotFoundException {
        Center center = centerService.findById(id);
        return center;
    }

    @GetMapping("/filtered/centers")
    public List<Center> findFilteredCenters(@RequestParam(name = "minCapacity") int capacity,
                                            @RequestParam(name = "changingRooms") boolean changingRooms,
                                            @RequestParam(name = "maxPrice") float subscriptionPrice) {
        List<Center> centers = centerService.findFilteredCenters(capacity, changingRooms, subscriptionPrice);
        return centers;
    }

    @PostMapping("/centers")
    public Center addCenter (@RequestBody CenterDTO centerDto) throws CityNotFoundException {
        Center center = centerService.addCenter(centerDto);
        return center;
    }

    @PutMapping("/center/{id}")
    public Center modifyCenter (@PathVariable long id, @RequestBody CenterDTO centerDto) throws CenterNotFoundException, CityNotFoundException {
        Center center = centerService.modifyCenter(id, centerDto);
        return center;
    }

    @DeleteMapping("/center/{id}")
    public Center deleteCenter (@PathVariable long id) throws CenterNotFoundException, CityNotFoundException {
        Center center = centerService.deleteCenter(id);
        return center;
    }

    @PatchMapping("/center/{id}")
    public Center partialCenterModification(@PathVariable long id, @RequestBody Map<Object, Object> fields) throws CenterNotFoundException, CityNotFoundException {
        Center center = centerService.partialCenterModification(id, fields);
        return center;
    }


    @ExceptionHandler(CenterNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCenterNotFoundException(CenterNotFoundException cenfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", cenfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCityNotFoundException(CityNotFoundException cinfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", cinfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
