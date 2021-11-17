package com.example.enjoypadelapi.service;

import com.example.enjoypadelapi.domain.Match;
import com.example.enjoypadelapi.exception.MatchNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MatchService {
    List<Match> findAll();
    Match findById(long id) throws MatchNotFoundException;
    Match addMatch(Match newMatch);
    Match deleteMatch(long id) throws MatchNotFoundException;
    Match modifyMatch(long id, Match newMatch);
}
