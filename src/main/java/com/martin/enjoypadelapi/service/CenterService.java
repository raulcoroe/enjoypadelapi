package com.martin.enjoypadelapi.service;

import com.martin.enjoypadelapi.domain.Center;
import com.martin.enjoypadelapi.domain.dto.CenterDTO;
import com.martin.enjoypadelapi.exception.CenterNotFoundException;
import com.martin.enjoypadelapi.exception.CityNotFoundException;

import java.util.List;
import java.util.Map;

public interface CenterService {
    List<Center> findAll();
    Center findById(long id) throws CenterNotFoundException;
    Center addCenter(CenterDTO newCenter) throws CityNotFoundException;
    Center deleteCenter(long id) throws CenterNotFoundException, CityNotFoundException;
    Center modifyCenter(long id, CenterDTO newCenter) throws CenterNotFoundException, CityNotFoundException;

    Center partialCenterModification(long id, Map<Object, Object> fields) throws CenterNotFoundException, CityNotFoundException;

    List<Center> findFilteredCenters(int capacity, boolean changingRooms, float subscriptionPrice);
}
