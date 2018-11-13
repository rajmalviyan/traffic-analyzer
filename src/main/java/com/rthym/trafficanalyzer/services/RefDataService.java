package com.rthym.trafficanalyzer.services;

import com.rthym.trafficanalyzer.model.Intersection;
import com.rthym.trafficanalyzer.model.Lane;

import java.util.List;


public interface RefDataService {

    List<String> getAllDistinctIntersectionIds();
    List<Intersection> getAllDistinctIntersections();
    List<Lane> getAllDistinctLanes();
}
