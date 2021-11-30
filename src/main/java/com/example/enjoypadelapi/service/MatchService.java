package com.example.enjoypadelapi.service;

import com.example.enjoypadelapi.domain.Match;
import com.example.enjoypadelapi.domain.Team;
import com.example.enjoypadelapi.domain.dto.MatchDTO;
import com.example.enjoypadelapi.exception.CourtNotFoundException;
import com.example.enjoypadelapi.exception.MatchNotFoundException;

import java.util.List;
import java.util.Map;

public interface MatchService {
    List<Match> findAll();
    Match findById(long id) throws MatchNotFoundException;
    Match addMatch(MatchDTO matchDto) throws CourtNotFoundException;
    Match deleteMatch(long id) throws MatchNotFoundException;
    Match modifyMatch(long id, MatchDTO matchDto) throws MatchNotFoundException, CourtNotFoundException;

    Match partialMatchModification(long id, Map<Object, Object> fields) throws MatchNotFoundException;

    List<Team> listMatchTeams(long id) throws MatchNotFoundException;
}
