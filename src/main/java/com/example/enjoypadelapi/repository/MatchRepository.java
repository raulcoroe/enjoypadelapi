package com.example.enjoypadelapi.repository;

import com.example.enjoypadelapi.domain.Match;
import com.example.enjoypadelapi.domain.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MatchRepository extends CrudRepository<Match, Long> {
    List<Match> findAll();
}
