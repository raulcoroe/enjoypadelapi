package com.example.enjoypadelapi.service;

import com.example.enjoypadelapi.domain.Team;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeamService {
    List<Team> findAll();
    Team findById(long id);
    Team addTeam(Team newTeam);
    Team deleteTeam(long id);
    Team modifyTeam(long id, Team newTeam);
}
