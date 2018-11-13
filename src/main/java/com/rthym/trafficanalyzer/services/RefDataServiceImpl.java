package com.rthym.trafficanalyzer.services;

import com.rthym.trafficanalyzer.model.Intersection;
import com.rthym.trafficanalyzer.model.Lane;
import com.rthym.trafficanalyzer.repositories.IntersectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RefDataServiceImpl implements RefDataService {

    @Autowired
    IntersectionRepository intersectionRepository;

    @Override
    public List<String> getAllDistinctIntersectionIds() {
        return intersectionRepository.findDistinctIntersectionId();
    }

    @Override
    public List<Intersection> getAllDistinctIntersections() {
        return  intersectionRepository.findAll();
    }

    @Override
    public List<Lane> getAllDistinctLanes() {
        return null;


    }
}
