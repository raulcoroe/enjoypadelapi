package com.example.enjoypadelapi.service;

import com.example.enjoypadelapi.domain.Center;
import com.example.enjoypadelapi.domain.Court;
import com.example.enjoypadelapi.domain.dto.CourtDTO;
import com.example.enjoypadelapi.exception.CenterNotFoundException;
import com.example.enjoypadelapi.exception.CourtNotFoundException;
import com.example.enjoypadelapi.repository.CenterRepository;
import com.example.enjoypadelapi.repository.CourtRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class CourtServiceImpl implements CourtService {

    @Autowired
    private CourtRepository courtRepository;

    @Autowired
    private CenterRepository centerRepository;

    @Override
    public List<Court> findAll() {
        List<Court> courts = courtRepository.findAll();
        return courts;
    }

    @Override
    public Court findById(long id) throws CourtNotFoundException {
        Court court = courtRepository.findById(id)
                .orElseThrow(()-> new CourtNotFoundException());
        return court;
    }

    @Override
    public Court addCourt (CourtDTO courtDto) throws CenterNotFoundException {

        ModelMapper mapper = new ModelMapper();
        Court court = mapper.map(courtDto, Court.class);

        Center center = centerRepository.findById(courtDto.getCenter())
                .orElseThrow(() -> new CenterNotFoundException());
        court.setCenter(center);

        return courtRepository.save(court);
    }

    @Override
    public Court deleteCourt (long id) throws CourtNotFoundException {
        Court court = courtRepository.findById(id)
                .orElseThrow(()-> new CourtNotFoundException());
        courtRepository.delete(court);
        return court;
    }

    @Override
    public Court modifyCourt(long id, CourtDTO courtDto) throws CourtNotFoundException, CenterNotFoundException {
        courtRepository.findById(id)
                .orElseThrow(()->new CourtNotFoundException());
        ModelMapper mapper = new ModelMapper();
        Court court = mapper.map(courtDto, Court.class);
        court.setId(id);

        if (courtDto.getCenter() != 0) {
            Center center = centerRepository.findById(courtDto.getCenter())
                    .orElseThrow(() -> new CenterNotFoundException());
            court.setCenter(center);
        } else {
            court.setCenter(null);
        }
        courtRepository.save(court);
        return court;
    }

    @Override
    public Court partialCourtModification(long id, Map<Object, Object> fields) throws CourtNotFoundException {
        Court court = courtRepository.findById(id)
                .orElseThrow(()-> new CourtNotFoundException());
        fields.forEach((k, v) -> {
            Field field = ReflectionUtils.findField(Court.class, (String) k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, court, v);
        });
        Court courtModified = courtRepository.save(court);
        return courtModified;
    }

    @Override
    public List<Court> findLightingCourts(boolean lighting) {
        return courtRepository.findLightingCourts(lighting);
    }
}
