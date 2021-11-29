package com.example.enjoypadelapi.service;

import com.example.enjoypadelapi.domain.Center;
import com.example.enjoypadelapi.domain.City;
import com.example.enjoypadelapi.exception.CenterNotFoundException;
import com.example.enjoypadelapi.exception.CityNotFoundException;
import com.example.enjoypadelapi.repository.CityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    CityRepository cityRepository;

    @Override
    public List<City> findAll() {
        List<City> cities = cityRepository.findAll();
        return cities;
    }

    @Override
    public City findById(long id) throws CityNotFoundException {
        City city = cityRepository.findById(id)
                .orElseThrow(()-> new CityNotFoundException());
        return city;
    }

    @Override
    public City addCity (City newCity) {
        City city = cityRepository.save(newCity);
        return city;
    }

    @Override
    public City deleteCity (long id) throws CityNotFoundException {
        City city = cityRepository.findById(id)
                .orElseThrow(()-> new CityNotFoundException());
        for (Center center : city.getCenters()) {
            center.setCity(null);
        }
        cityRepository.delete(city);
        return city;
    }

    @Override
    public City modifyCity(long id, City newCity) throws CityNotFoundException {
        cityRepository.findById(id)
                .orElseThrow(()->new CityNotFoundException());
        ModelMapper mapper = new ModelMapper();
        City city = mapper.map(newCity, City.class);
        city.setId(id);
        cityRepository.save(city);
        return city;
    }

    @Override
    public City partialCityModification(long id, Map<Object, Object> fields) throws CityNotFoundException {
        City city = cityRepository.findById(id)
                .orElseThrow(()-> new CityNotFoundException());
        fields.forEach((k, v) -> {
            Field field = ReflectionUtils.findField(City.class, (String) k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, city, v);
        });
        City cityModified = modifyCity(id, city);
        return cityModified;
    }
}
