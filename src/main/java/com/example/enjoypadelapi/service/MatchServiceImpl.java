package com.example.enjoypadelapi.service;

import com.example.enjoypadelapi.domain.Court;
import com.example.enjoypadelapi.domain.Match;
import com.example.enjoypadelapi.domain.Team;
import com.example.enjoypadelapi.domain.dto.MatchDTO;
import com.example.enjoypadelapi.exception.CourtNotFoundException;
import com.example.enjoypadelapi.exception.MatchNotFoundException;
import com.example.enjoypadelapi.repository.CourtRepository;
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
    private MatchRepository matchRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private CourtRepository courtRepository;

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
    public Match addMatch(MatchDTO matchDto) throws CourtNotFoundException {
        Court court = courtRepository.findById(matchDto.getCourt())
                .orElseThrow(()-> new CourtNotFoundException());

        ModelMapper mapper = new ModelMapper();
        Match match = mapper.map(matchDto, Match.class);
        match.setCourt(court);
        matchRepository.save(match);
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

    @Override
    public List<Team> listMatchTeams(long id) throws MatchNotFoundException {
        Match match = matchRepository.findById(id)
                .orElseThrow(()-> new MatchNotFoundException());
        List<Team> teams = match.getTeams();
        return teams;
    }
}
