package com.example.enjoypadelapi.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "start_time")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime startTime;
    @Column(name = "end_time")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime endTime;
    @Column
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;
    @Column(name = "match_score")
    private String matchScore;

    @ManyToMany(mappedBy = "matches")
    private List<Team> teams;

}

