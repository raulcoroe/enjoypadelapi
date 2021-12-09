package com.martin.enjoypadelapi.repository;

import com.martin.enjoypadelapi.domain.Match;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MatchRepository extends CrudRepository<Match, Long> {
    List<Match> findAll();
}
