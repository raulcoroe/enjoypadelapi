package com.example.enjoypadelapi.service;

import com.example.enjoypadelapi.domain.Match;
import com.example.enjoypadelapi.repository.MatchRepository;
import com.example.enjoypadelapi.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MatchServiceImpl implements  MatchService{

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    TeamRepository teamRepository;

    @Override
    public List<Match> findAll() {
        return null;
    }

    @Override
    public Match findById() {
        return null;
    }

    @Override
    public Match addMatch(Match newMatch) {
        return null;
    }

    @Override
    public Match deleteMatch(long id) {
        return null;
    }

    @Override
    public Match modifyMatch(long id, Match newMatch) {
        return null;
    }
}
