package com.example.enjoypadelapi.service;

import com.example.enjoypadelapi.domain.Center;
import com.example.enjoypadelapi.domain.City;
import com.example.enjoypadelapi.domain.Court;
import com.example.enjoypadelapi.domain.Player;
import com.example.enjoypadelapi.domain.dto.CenterDTO;
import com.example.enjoypadelapi.exception.CenterNotFoundException;
import com.example.enjoypadelapi.exception.CityNotFoundException;
import com.example.enjoypadelapi.repository.CenterRepository;
import com.example.enjoypadelapi.repository.CityRepository;
import com.example.enjoypadelapi.repository.CourtRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class CenterServiceImpl implements CenterService {

    @Autowired
    private CenterRepository centerRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CourtRepository courtRepository;


    @Override
    public List<Center> findAll() {
        List<Center> centers = centerRepository.findAll();
        return centers;
    }

    @Override
    public Center findById(long id) throws CenterNotFoundException {
        Center center = centerRepository.findById(id)
                .orElseThrow(()-> new CenterNotFoundException());
        return center;
    }

    @Override
    public Center addCenter (CenterDTO centerDto) throws CityNotFoundException {

        ModelMapper mapper = new ModelMapper();
        Center center = mapper.map(centerDto, Center.class);

        if (centerDto.getCity() != 0) {
            City city = cityRepository.findById(centerDto.getCity())
                    .orElseThrow(() -> new CityNotFoundException());
            center.setCity(city);
        } else {
            center.setCity(null);
        }
        return centerRepository.save(center);
    }

    @Override
    public Center deleteCenter (long id) throws CenterNotFoundException, CityNotFoundException {
        Center center = centerRepository.findById(id)
                .orElseThrow(() -> new CenterNotFoundException());

        for (Court court : center.getCourts()){
            courtRepository.save(court);
        }
        centerRepository.delete(center);
        return center;
    }

    @Override
    public Center modifyCenter(long id, CenterDTO centerDto) throws CenterNotFoundException, CityNotFoundException {
        centerRepository.findById(id)
                .orElseThrow(()->new CenterNotFoundException());
        ModelMapper mapper = new ModelMapper();
        Center center = mapper.map(centerDto, Center.class);
        center.setId(id);
        if (centerDto.getCity() != 0) {
            City city = cityRepository.findById(centerDto.getCity())
                    .orElseThrow(() -> new CityNotFoundException());
            center.setCity(city);
        } else {
            center.setCity(null);
        }
        centerRepository.save(center);
        return center;
    }


    @Override
    public Center partialCenterModification(long id, Map<Object, Object> fields) throws CenterNotFoundException {
        Center center = centerRepository.findById(id)
                .orElseThrow(()-> new CenterNotFoundException());

        fields.forEach((k, v) -> {
            Field field = ReflectionUtils.findField(Center.class, (String) k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, center, v);
        });
        Center centerModified = centerRepository.save(center);
        return centerModified;
    }

    @Override
    public List<Center> findFilteredCenters(int capacity, boolean changingRooms, float subscriptionPrice) {
        List<Center> centers = centerRepository.findFilteredCenters(capacity, changingRooms, subscriptionPrice);
        return centers;
    }
}
