package com.example.enjoypadelapi.service;

import com.example.enjoypadelapi.domain.Match;
import com.example.enjoypadelapi.exception.MatchNotFoundException;
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
        List<Match> matches = matchRepository.findAll();
        return matches;
    }

    @Override
    public Match findById(long id) throws MatchNotFoundException {
        Match match = matchRepository.findById(id)
                .orElseThrow(()-> new MatchNotFoundException());
        return match;
    }

    @Override
    public Match addMatch(Match newMatch) {
        Match match = matchRepository.save(newMatch);
        return match;
    }

    @Override
    public Match deleteMatch(long id) throws MatchNotFoundException {
        Match match = matchRepository.findById(id)
                .orElseThrow(()-> new MatchNotFoundException());
        matchRepository.delete(match);
        return match;
    }

    @Override
    public Match modifyMatch(long id, Match newMatch) {
        return null;
    }
}
