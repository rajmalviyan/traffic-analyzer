package com.rthym.trafficanalyzer.repositories;

import com.rthym.trafficanalyzer.model.Intersection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.xml.ws.WebEndpoint;
import java.util.Date;
import java.util.List;

public interface IntersectionRepository extends JpaRepository<Intersection, String> {
    @Query("select distinct intersectionId from Intersection")
    List<String> findDistinctIntersectionId();

    @Query(value = "select * From Intersection i join Lane_intersection li on i.Id= li.intersection_id  join Lane l on li.lane_id=l.id where i.intersectionId=:intersectionId and i.date=:date and l.id=:laneId", nativeQuery = true)
    List<Intersection> findAllIntersectionsByIntersectionIdAndDate(String intersectionId, Date date, int laneId);

}
