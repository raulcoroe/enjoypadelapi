package com.example.enjoypadelapi.service;

import com.example.enjoypadelapi.domain.City;
import com.example.enjoypadelapi.domain.Court;
import com.example.enjoypadelapi.exception.CityNotFoundException;
import com.example.enjoypadelapi.exception.CourtNotFoundException;

import java.util.List;
import java.util.Map;

public interface CityService {
    List<City> findAll();
    City findById(long id) throws CityNotFoundException;
    City addCity(City newCity);
    City deleteCity(long id) throws CityNotFoundException;
    City modifyCity(long id, City newCity) throws CityNotFoundException;
    City partialCityModification(long id, Map<Object, Object> fields) throws CityNotFoundException;
}
