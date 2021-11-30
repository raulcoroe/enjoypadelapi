package com.example.enjoypadelapi.repository;

import com.example.enjoypadelapi.domain.Center;
import com.example.enjoypadelapi.domain.Match;
import com.example.enjoypadelapi.domain.Player;
import com.example.enjoypadelapi.domain.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {
    List<Team> findAll();

    @Query(value = "select * from \"teams\" where (\"professional\" = :professional)", nativeQuery = true)
    List<Team> findProfessionalTeams(boolean professional);
}
