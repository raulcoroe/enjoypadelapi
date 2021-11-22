package com.example.enjoypadelapi.service;

import com.example.enjoypadelapi.domain.Player;
import com.example.enjoypadelapi.domain.Team;
import com.example.enjoypadelapi.exception.FullTeamException;
import com.example.enjoypadelapi.exception.TeamNotFoundException;
import com.example.enjoypadelapi.exception.PlayerNotFoundException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import java.util.List;

public interface PlayerService {
    List<Player> findAll();

    Player findById(long id) throws PlayerNotFoundException;

    Player addPlayer(Player newPlayer);

    Player deletePlayer(long id) throws PlayerNotFoundException;

    Team addPlayerToTeam(long player_id, long team_id) throws PlayerNotFoundException, TeamNotFoundException, FullTeamException;

    List<Team> listPlayerTeams(long id) throws PlayerNotFoundException;

    Team deletePlayerToTeam(long player_id, long team_id) throws PlayerNotFoundException, TeamNotFoundException;

    Player modifyPlayer(long id, Player newPlayer) throws PlayerNotFoundException;
}
