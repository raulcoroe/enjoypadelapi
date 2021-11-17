package com.example.enjoypadelapi.service;

import com.example.enjoypadelapi.domain.Team;
import com.example.enjoypadelapi.repository.MatchRepository;
import com.example.enjoypadelapi.repository.TeamRepository;
import com.example.enjoypadelapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TeamServiceImpl implements TeamService {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MatchRepository matchRepository;

    @Override
    public List<Team> findAll() {
        return null;
    }

    @Override
    public Team findById(long id) {
        return null;
    }

    @Override
    public Team addTeam(Team newTeam) {
        return null;
    }

    @Override
    public Team deleteTeam(long id) {
        return null;
    }

    @Override
    public Team modifyTeam(long id, Team newTeam) {
        return null;
    }
}
