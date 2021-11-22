package com.example.enjoypadelapi.repository;

import com.example.enjoypadelapi.domain.Player;
import com.example.enjoypadelapi.domain.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {
    List<Player> findAll();
    List<Team> findByPlayer(Player player);
}
