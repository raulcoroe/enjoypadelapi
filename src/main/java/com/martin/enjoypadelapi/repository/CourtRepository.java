package com.martin.enjoypadelapi.repository;

import com.martin.enjoypadelapi.domain.Court;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourtRepository extends CrudRepository<Court, Long> {
    List<Court> findAll();

    @Query(value = "select * from \"courts\" where (\"lighting\" = :lighting)", nativeQuery = true)
    List<Court> findLightingCourts(boolean lighting);
}
