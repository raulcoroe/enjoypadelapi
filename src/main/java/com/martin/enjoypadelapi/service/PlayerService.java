package com.martin.enjoypadelapi.service;

import com.martin.enjoypadelapi.domain.Player;
import com.martin.enjoypadelapi.domain.Team;
import com.martin.enjoypadelapi.exception.FullTeamException;
import com.martin.enjoypadelapi.exception.TeamNotFoundException;
import com.martin.enjoypadelapi.exception.PlayerNotFoundException;

import java.util.List;
import java.util.Map;

public interface PlayerService {
    List<Player> findAll();

    Player findById(long id) throws PlayerNotFoundException;

    Player addPlayer(Player newPlayer);

    Player deletePlayer(long id) throws PlayerNotFoundException;

    Team addPlayerToTeam(long player_id, long team_id) throws PlayerNotFoundException, TeamNotFoundException, FullTeamException;

    List<Team> listPlayerTeams(long id) throws PlayerNotFoundException;

    Team deletePlayerToTeam(long player_id, long team_id) throws PlayerNotFoundException, TeamNotFoundException;

    Player modifyPlayer(long id, Player newPlayer) throws PlayerNotFoundException;

    Player partialPlayerModification(long id, Map<Object, Object> fields) throws PlayerNotFoundException;
}
