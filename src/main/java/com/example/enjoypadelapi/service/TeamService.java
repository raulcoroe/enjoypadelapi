package com.example.enjoypadelapi.service;

import com.example.enjoypadelapi.domain.Match;
import com.example.enjoypadelapi.domain.Player;
import com.example.enjoypadelapi.domain.Team;
import com.example.enjoypadelapi.exception.FullMatchException;
import com.example.enjoypadelapi.exception.MatchNotFoundException;
import com.example.enjoypadelapi.exception.PlayerNotFoundException;
import com.example.enjoypadelapi.exception.TeamNotFoundException;

import java.util.List;
import java.util.Map;

public interface TeamService {
    List<Team> findAll();

    Team findById(long id) throws TeamNotFoundException;

    Team addTeam(Team newTeam);

    Team deleteTeam(long id) throws TeamNotFoundException, PlayerNotFoundException;

    Team modifyTeam(long id, Team newTeam) throws TeamNotFoundException;

    List<Player> listTeamPlayers(long id) throws TeamNotFoundException;

    Team addTeamToMatch(long match_id, long team_id) throws TeamNotFoundException, MatchNotFoundException, FullMatchException;

    Match deletePlayerToTeam(long match_id, long team_id) throws TeamNotFoundException, MatchNotFoundException;

    Team partialTeamModification(long id, Map<Object, Object> fields) throws TeamNotFoundException;

    List<Match> listTeamMatches(long id) throws TeamNotFoundException;
}
