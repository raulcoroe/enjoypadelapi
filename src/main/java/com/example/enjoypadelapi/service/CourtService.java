package com.example.enjoypadelapi.service;

import com.example.enjoypadelapi.domain.Court;
import com.example.enjoypadelapi.domain.dto.CourtDTO;
import com.example.enjoypadelapi.exception.CenterNotFoundException;
import com.example.enjoypadelapi.exception.CourtNotFoundException;

import java.util.List;
import java.util.Map;

public interface CourtService {
    List<Court> findAll();
    Court findById(long id) throws CourtNotFoundException;
    Court addCourt(CourtDTO courtDto) throws CenterNotFoundException;
    Court deleteCourt(long id) throws CourtNotFoundException;
    Court modifyCourt(long id, CourtDTO newCourt) throws CourtNotFoundException, CenterNotFoundException;
    Court partialCourtModification(long id, Map<Object, Object> fields) throws CourtNotFoundException;
}
