package com.example.enjoypadelapi.service;

import com.example.enjoypadelapi.domain.Match;
import com.example.enjoypadelapi.domain.Player;
import com.example.enjoypadelapi.domain.Team;
import com.example.enjoypadelapi.exception.MatchNotFoundException;
import com.example.enjoypadelapi.exception.PlayerNotFoundException;
import com.example.enjoypadelapi.repository.MatchRepository;
import com.example.enjoypadelapi.repository.PlayerRepository;
import com.example.enjoypadelapi.repository.TeamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class MatchServiceImpl implements  MatchService{

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Override
    public List<Match> findAll() {
        List<Match> matches = matchRepository.findAll();
        return matches;
    }

    @Override
    public Match findById(long id) throws MatchNotFoundException {
        Match match = matchRepository.findById(id)
                .orElseThrow(()-> new MatchNotFoundException());
        return match;
    }

    @Override
    public Match addMatch(Match newMatch) {
        Match match = matchRepository.save(newMatch);
        return match;
    }

    @Override
    public Match deleteMatch(long id) throws MatchNotFoundException {
        Match match = matchRepository.findById(id)
                .orElseThrow(()-> new MatchNotFoundException());
        for (Team team : match.getTeams()){
            team.getMatches().remove(match);
            teamRepository.save(team);
        }
        matchRepository.delete(match);
        return match;
    }

    @Override
    public Match modifyMatch(long id, Match newMatch) throws MatchNotFoundException {
        matchRepository.findById(id)
                .orElseThrow(()->new MatchNotFoundException());
        ModelMapper mapper = new ModelMapper();
        Match match = mapper.map(newMatch, Match.class);
        match.setId(id);
        matchRepository.save(match);
        return match;
    }

    @Override
    public Match partialMatchModification(long id, Map<Object, Object> fields) throws MatchNotFoundException {
        Match match = matchRepository.findById(id)
                .orElseThrow(()-> new MatchNotFoundException());
        fields.forEach((k, v) -> {
            Field field = ReflectionUtils.findField(Match.class, (String) k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, match, v);
        });
        Match matchModified = modifyMatch(id, match);
        return matchModified;
    }
}
