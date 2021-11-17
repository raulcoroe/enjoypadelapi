package com.example.enjoypadelapi.service;

import com.example.enjoypadelapi.domain.Team;
import com.example.enjoypadelapi.exception.TeamNotFoundException;
import com.example.enjoypadelapi.repository.MatchRepository;
import com.example.enjoypadelapi.repository.TeamRepository;
import com.example.enjoypadelapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.FileSystemAlreadyExistsException;
import java.util.List;
@Service
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
    public Team findById(long id) throws TeamNotFoundException {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new TeamNotFoundException());
        return team;
    }

    @Override
    public Team addTeam(Team newTeam) {
        Team team = teamRepository.save(newTeam);
        return team;
    }

    @Override
    public Team deleteTeam(long id) throws TeamNotFoundException {
        Team team = teamRepository.findById(id)
                .orElseThrow(()-> new TeamNotFoundException());
        teamRepository.delete(team);
        return team;
    }

    @Override
    public Team modifyTeam(long id, Team newTeam) {
        return null;
    }
}
