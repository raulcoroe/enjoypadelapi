package com.example.enjoypadelapi.service;

import com.example.enjoypadelapi.domain.Court;
import com.example.enjoypadelapi.exception.CourtNotFoundException;
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
    CourtRepository courtRepository;

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
    public Court addCourt (Court newCourt) {
        Court court = courtRepository.save(newCourt);
        return court;
    }

    @Override
    public Court deleteCourt (long id) throws CourtNotFoundException {
        Court court = courtRepository.findById(id)
                .orElseThrow(()-> new CourtNotFoundException());
        courtRepository.delete(court);
        return court;
    }

    @Override
    public Court modifyCourt(long id, Court newCourt) throws CourtNotFoundException {
        courtRepository.findById(id)
                .orElseThrow(()->new CourtNotFoundException());
        ModelMapper mapper = new ModelMapper();
        Court court = mapper.map(newCourt, Court.class);
        court.setId(id);
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
        Court courtModified = modifyCourt(id, court);
        return courtModified;
    }
}
