package com.example.enjoypadelapi.repository;

import com.example.enjoypadelapi.domain.Court;
import com.example.enjoypadelapi.domain.Match;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourtRepository extends CrudRepository<Court, Long> {
    List<Court> findAll();
}
