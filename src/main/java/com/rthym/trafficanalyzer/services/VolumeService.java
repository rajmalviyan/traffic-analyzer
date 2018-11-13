package com.rthym.trafficanalyzer.services;

import com.rthym.trafficanalyzer.model.Intersection;

import java.util.Date;
import java.util.List;

public interface VolumeService {

    List<Intersection> getVolumeByIntersectionIdAndDate();

    List<Intersection> syncVolumeByIntersectionIdAndDate(String intersectionId, String date);

    List<Intersection> insertInToVolumeByIntersectionIdAndDate(String intersectionId, List<Intersection> intersections, Date date);

    List<Intersection> getVolumeByIntersectionIdDateAndLane(String intersectionId, String date, Integer laneId);
}
