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
@Entity(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private boolean professional;
    @Column
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate creationDate;

    @ManyToMany(mappedBy = "teams")
    private List<Player> players;

    @JoinTable(
            name = "rel_teams_matches",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "match_id")
    )
    @ManyToMany
    @JsonBackReference (value = "team_matches")
    private List<Match> matches;
}
