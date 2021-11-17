package com.example.enjoypadelapi.repository;

import com.example.enjoypadelapi.domain.Match;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MatchRepository {
    List<Match> findAll();
}
