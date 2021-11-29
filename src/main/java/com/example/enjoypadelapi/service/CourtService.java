package com.example.enjoypadelapi.service;

import com.example.enjoypadelapi.domain.Court;
import com.example.enjoypadelapi.domain.Match;
import com.example.enjoypadelapi.domain.Team;
import com.example.enjoypadelapi.exception.CourtNotFoundException;
import com.example.enjoypadelapi.exception.MatchNotFoundException;

import java.util.List;
import java.util.Map;

public interface CourtService {
    List<Court> findAll();
    Court findById(long id) throws CourtNotFoundException;
    Court addCourt(Court newCourt);
    Court deleteCourt(long id) throws CourtNotFoundException;
    Court modifyCourt(long id, Court newCourt) throws CourtNotFoundException;
    Court partialCourtModification(long id, Map<Object, Object> fields) throws CourtNotFoundException;
}
