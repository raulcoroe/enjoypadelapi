package com.example.enjoypadelapi.repository;

import com.example.enjoypadelapi.domain.Team;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository {
    List<Team> findAll();
}
