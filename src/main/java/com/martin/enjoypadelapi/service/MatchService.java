package com.martin.enjoypadelapi.service;

import com.martin.enjoypadelapi.domain.Match;
import com.martin.enjoypadelapi.domain.Team;
import com.martin.enjoypadelapi.domain.dto.MatchDTO;
import com.martin.enjoypadelapi.exception.CourtNotFoundException;
import com.martin.enjoypadelapi.exception.MatchNotFoundException;

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
