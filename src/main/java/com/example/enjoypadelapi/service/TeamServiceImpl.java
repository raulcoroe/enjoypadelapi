package com.example.enjoypadelapi.service;

import com.example.enjoypadelapi.domain.Match;
import com.example.enjoypadelapi.domain.Team;
import com.example.enjoypadelapi.domain.Player;
import com.example.enjoypadelapi.exception.FullMatchException;
import com.example.enjoypadelapi.exception.MatchNotFoundException;
import com.example.enjoypadelapi.exception.TeamNotFoundException;
import com.example.enjoypadelapi.repository.MatchRepository;
import com.example.enjoypadelapi.repository.TeamRepository;
import com.example.enjoypadelapi.repository.PlayerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    PlayerService playerService;

    @Override
    public List<Team> findAll() {
        List<Team> teams = teamRepository.findAll();
        return teams;
    }

    @Override
    public Team findById(long id) throws TeamNotFoundException {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new TeamNotFoundException());
        return team;
    }

    @Override
    public Team addTeam(Team newTeam) {
        Team team = teamRepository.save(newTeam);
        return team;
    }

    @Override
    public Team deleteTeam(long id) throws TeamNotFoundException {
        Team team = teamRepository.findById(id)
                .orElseThrow(()-> new TeamNotFoundException());

        for (Player player : team.getPlayers()){
            player.getTeams().remove(team);
            playerRepository.save(player);
        }
        teamRepository.delete(team);
        return team;
    }

    @Override
    public Team modifyTeam(long id, Team newTeam) throws TeamNotFoundException {
        teamRepository.findById(id)
                .orElseThrow(()->new TeamNotFoundException());
        ModelMapper mapper = new ModelMapper();
        Team team = mapper.map(newTeam, Team.class);
        team.setId(id);
        teamRepository.save(team);
        return team;
    }

    @Override
    public List<Player> listTeamPlayers(long id) throws TeamNotFoundException {
        Team team = teamRepository.findById(id)
                .orElseThrow(()-> new TeamNotFoundException());
        List<Player> players = team.getPlayers();
        return players;
    }

    @Override
    public Team addTeamToMatch(long match_id, long team_id) throws TeamNotFoundException, MatchNotFoundException, FullMatchException {
        Team team = teamRepository.findById(team_id)
                .orElseThrow(()->new TeamNotFoundException());
        Match match = matchRepository.findById(match_id)
                .orElseThrow(()->new MatchNotFoundException());
        if (match.getTeams().size() < 2){
            team.getMatches().add(match);
            teamRepository.save(team);
            return team;
        } else {
            throw new FullMatchException();
        }

    }

    @Override
    public Match deletePlayerToTeam(long match_id, long team_id) throws TeamNotFoundException, MatchNotFoundException {
        Team team = teamRepository.findById(team_id)
                .orElseThrow(()->new TeamNotFoundException());
        Match match = matchRepository.findById(match_id)
                .orElseThrow(()->new MatchNotFoundException());
        team.getMatches().remove(match);
        teamRepository.save(team);
        return match;
    }
}
