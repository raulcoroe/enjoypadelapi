package com.martin.enjoypadelapi.service;

import com.martin.enjoypadelapi.domain.Match;
import com.martin.enjoypadelapi.domain.Player;
import com.martin.enjoypadelapi.domain.Team;
import com.martin.enjoypadelapi.exception.FullMatchException;
import com.martin.enjoypadelapi.exception.MatchNotFoundException;
import com.martin.enjoypadelapi.exception.PlayerNotFoundException;
import com.martin.enjoypadelapi.exception.TeamNotFoundException;

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

    List<Team> findProfessionalTeams(boolean professional);
}
