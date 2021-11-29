package com.example.enjoypadelapi.repository;

import com.example.enjoypadelapi.domain.Center;
import com.example.enjoypadelapi.domain.Match;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CenterRepository extends CrudRepository<Center, Long> {
    List<Center> findAll();
}
