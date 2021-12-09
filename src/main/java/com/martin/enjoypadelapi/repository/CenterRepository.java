package com.martin.enjoypadelapi.repository;

import com.martin.enjoypadelapi.domain.Center;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CenterRepository extends CrudRepository<Center, Long> {
    List<Center> findAll();

    @Query(value = "select * from \"center\" where (\"capacity\" >= :capacity AND \"changing_rooms\" = :changingRooms AND \"subscription_price\" < :subscriptionPrice)", nativeQuery = true)
    List<Center> findFilteredCenters(int capacity, boolean changingRooms, float subscriptionPrice);

}
