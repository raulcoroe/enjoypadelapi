package com.example.enjoypadelapi.service;

import com.example.enjoypadelapi.domain.Team;
import com.example.enjoypadelapi.exception.TeamNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeamService {
    List<Team> findAll();
    Team findById(long id) throws TeamNotFoundException;
    Team addTeam(Team newTeam);
    Team deleteTeam(long id) throws TeamNotFoundException;
    Team modifyTeam(long id, Team newTeam);
}
