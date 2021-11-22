package com.example.enjoypadelapi.service;

import com.example.enjoypadelapi.domain.Match;
import com.example.enjoypadelapi.exception.MatchNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface MatchService {
    List<Match> findAll();
    Match findById(long id) throws MatchNotFoundException;
    Match addMatch(Match newMatch);
    Match deleteMatch(long id) throws MatchNotFoundException;
    Match modifyMatch(long id, Match newMatch) throws MatchNotFoundException;

    Match partialMatchModification(long id, Map<Object, Object> fields) throws MatchNotFoundException;
}
