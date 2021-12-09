package com.martin.enjoypadelapi.controller;

import com.martin.enjoypadelapi.domain.Center;
import com.martin.enjoypadelapi.domain.dto.CenterDTO;
import com.martin.enjoypadelapi.exception.CenterNotFoundException;
import com.martin.enjoypadelapi.exception.CityNotFoundException;
import com.martin.enjoypadelapi.exception.ErrorResponse;
import com.martin.enjoypadelapi.service.CenterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CenterController {

    private final Logger logger = LoggerFactory.getLogger(PlayerController.class);

    @Autowired
    private CenterService centerService;

    @GetMapping("/centers")
    public List<Center> findAll() {
        logger.info("Inicio findAll(center)");
        List<Center> centers = centerService.findAll();
        logger.info("Final findAll(center)");
        return centers;
    }

    @GetMapping("/center/{id}")
    public Center findById(@PathVariable long id) throws CenterNotFoundException {
        logger.info("Inicio findById(center)");
        Center center = centerService.findById(id);
        logger.info("Final findById(center)");
        return center;
    }

    @GetMapping("/filtered/centers")
    public List<Center> findFilteredCenters(@RequestParam(name = "minCapacity") int capacity,
                                            @RequestParam(name = "changingRooms") boolean changingRooms,
                                            @RequestParam(name = "maxPrice") float subscriptionPrice) {
        logger.info("Inicio findFilteredCenters");
        List<Center> centers = centerService.findFilteredCenters(capacity, changingRooms, subscriptionPrice);
        logger.info("Final findFilteredCenters");
        return centers;
    }

    @PostMapping("/centers")
    public Center addCenter (@RequestBody CenterDTO centerDto) throws CityNotFoundException {
        logger.info("Inicio addCenter");
        Center center = centerService.addCenter(centerDto);
        logger.info("Final addCenter");
        return center;
    }

    @PutMapping("/center/{id}")
    public Center modifyCenter (@PathVariable long id, @RequestBody CenterDTO centerDto) throws CenterNotFoundException, CityNotFoundException {
        logger.info("Inicio modifyCenter");
        Center center = centerService.modifyCenter(id, centerDto);
        logger.info("Final modifyCenter");
        return center;
    }

    @DeleteMapping("/center/{id}")
    public Center deleteCenter (@PathVariable long id) throws CenterNotFoundException, CityNotFoundException {
        logger.info("Inicio deleteCenter");
        Center center = centerService.deleteCenter(id);
        logger.info("Final deleteCenter");
        return center;
    }

    @PatchMapping("/center/{id}")
    public Center partialCenterModification(@PathVariable long id, @RequestBody Map<Object, Object> fields) throws CenterNotFoundException, CityNotFoundException {
        logger.info("Inicio partialCenterModification");
        Center center = centerService.partialCenterModification(id, fields);
        logger.info("Final partialCenterModification");
        return center;
    }


    @ExceptionHandler(CenterNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCenterNotFoundException(CenterNotFoundException cenfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", cenfe.getMessage());
        logger.error(cenfe.getMessage(), cenfe);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCityNotFoundException(CityNotFoundException cinfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", cinfe.getMessage());
        logger.error(cinfe.getMessage(), cinfe);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
