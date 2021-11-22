package com.example.enjoypadelapi.service;

import com.example.enjoypadelapi.domain.Player;
import com.example.enjoypadelapi.domain.Team;
import com.example.enjoypadelapi.exception.FullTeamException;
import com.example.enjoypadelapi.exception.TeamNotFoundException;
import com.example.enjoypadelapi.exception.PlayerNotFoundException;
import com.example.enjoypadelapi.repository.TeamRepository;
import com.example.enjoypadelapi.repository.PlayerRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    TeamRepository teamRepository;

    @Override
    public List<Player> findAll() {
        List<Player> players = playerRepository.findAll();
        return players;
    }

    @Override
    public Player findById(long id) throws PlayerNotFoundException {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException());
        return player;
    }

    @Override
    public Player addPlayer(Player newPlayer) {
        Player player = playerRepository.save(newPlayer);
        return player;
    }

    @Override
    public Player deletePlayer(long id) throws PlayerNotFoundException {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException());
        playerRepository.delete(player);
        return player;
    }

    @Override
    public Player modifyPlayer(long id, Player newPlayer) throws PlayerNotFoundException {
        playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException());
        ModelMapper mapper = new ModelMapper();
        Player player = mapper.map(newPlayer, Player.class);
        player.setId(id);
        playerRepository.save(player);
        return player;
    }

    @Override
    public Team addPlayerToTeam(long player_id, long team_id) throws PlayerNotFoundException, TeamNotFoundException, FullTeamException {
        Player player = playerRepository.findById(player_id)
                .orElseThrow(() -> new PlayerNotFoundException());
        Team team = teamRepository.findById(team_id)
                .orElseThrow(() -> new TeamNotFoundException());
        if (team.getPlayers().size() < 2) {
            player.getTeams().add(team);
            playerRepository.save(player);
            return team;
        } else {
            throw new FullTeamException();
        }
    }

    @Override
    public List<Team> listPlayerTeams(long id) throws PlayerNotFoundException {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException());

        List<Team> teams = player.getTeams();
        return teams;
    }

    @Override
    public Team deletePlayerToTeam(long player_id, long team_id) throws PlayerNotFoundException, TeamNotFoundException {
        Player player = playerRepository.findById(player_id)
                .orElseThrow(() -> new PlayerNotFoundException());
        Team team = teamRepository.findById(team_id)
                .orElseThrow(() -> new TeamNotFoundException());
        player.getTeams().remove(team);
        playerRepository.save(player);
        return team;
    }
}
