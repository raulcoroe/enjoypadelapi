package com.example.enjoypadelapi.repository;

import com.example.enjoypadelapi.domain.City;
import com.example.enjoypadelapi.domain.Match;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {
    List<City> findAll();
}
