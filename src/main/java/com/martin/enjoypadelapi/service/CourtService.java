package com.martin.enjoypadelapi.service;

import com.martin.enjoypadelapi.domain.Court;
import com.martin.enjoypadelapi.domain.dto.CourtDTO;
import com.martin.enjoypadelapi.exception.CenterNotFoundException;
import com.martin.enjoypadelapi.exception.CourtNotFoundException;

import java.util.List;
import java.util.Map;

public interface CourtService {
    List<Court> findAll();
    Court findById(long id) throws CourtNotFoundException;
    Court addCourt(CourtDTO courtDto) throws CenterNotFoundException;
    Court deleteCourt(long id) throws CourtNotFoundException;
    Court modifyCourt(long id, CourtDTO newCourt) throws CourtNotFoundException, CenterNotFoundException;
    Court partialCourtModification(long id, Map<Object, Object> fields) throws CourtNotFoundException;

    List<Court> findLightingCourts(boolean lighting);
}
