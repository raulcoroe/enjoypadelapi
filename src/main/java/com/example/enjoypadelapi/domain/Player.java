package com.example.enjoypadelapi.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column(name = "player_score")
    private long playerScore;
    @Column(name = "birth_date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDate;

    @JoinTable(
            name = "rel_players_teams",
            joinColumns = @JoinColumn(name = "players_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    @ManyToMany(cascade = CascadeType.ALL)
    @JsonBackReference(value = "player_teams")
    private List<Team> teams;

}
