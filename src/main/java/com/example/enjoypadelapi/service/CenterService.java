package com.example.enjoypadelapi.service;

import com.example.enjoypadelapi.domain.Center;
import com.example.enjoypadelapi.domain.dto.CenterDTO;
import com.example.enjoypadelapi.exception.CenterNotFoundException;
import com.example.enjoypadelapi.exception.CityNotFoundException;

import java.util.List;
import java.util.Map;

public interface CenterService {
    List<Center> findAll();
    Center findById(long id) throws CenterNotFoundException;
    Center addCenter(CenterDTO newCenter) throws CityNotFoundException;
    Center deleteCenter(long id) throws CenterNotFoundException, CityNotFoundException;
    Center modifyCenter(long id, CenterDTO newCenter) throws CenterNotFoundException, CityNotFoundException;

    Center partialCenterModification(long id, Map<Object, Object> fields) throws CenterNotFoundException, CityNotFoundException;
}
