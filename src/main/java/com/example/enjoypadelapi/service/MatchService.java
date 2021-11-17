package com.example.enjoypadelapi.service;

import com.example.enjoypadelapi.domain.Match;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MatchService {
    List<Match> findAll();
    Match findById();
    Match addMatch(Match newMatch);
    Match deleteMatch(long id);
    Match modifyMatch(long id, Match newMatch);
}
