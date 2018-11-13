package com.rthym.trafficanalyzer.services;

import com.rthym.trafficanalyzer.model.Intersection;
import com.rthym.trafficanalyzer.model.Lane;
import com.rthym.trafficanalyzer.model.Vehicle;
import com.rthym.trafficanalyzer.repositories.IntersectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Component
public class VolumeServiceImpl implements VolumeService {

    @Autowired
    private IntersectionApiCallerServiceImpl intersectionApiCallerService;

    @Autowired
    private IntersectionRepository intersectionRepository;

    @Override
    public List<Intersection> getVolumeByIntersectionIdAndDate() {
        return intersectionRepository.findAll();
    }

    @Override
    public List<Intersection> syncVolumeByIntersectionIdAndDate(String intersectionId, String date) {
        return intersectionApiCallerService.syncDataFromExternalApi(intersectionId, date);
    }

    @Override
    public List<Intersection> insertInToVolumeByIntersectionIdAndDate(String intersectionId, List<Intersection> intersections, Date date) {

        for (Intersection intersection : intersections) {
            intersection.setIntersectionId(intersectionId);
            intersection.setDate(date);
            intersection.setId(null);
            Set<Lane> lanes=intersection.getLanes();
            for (Lane lane:lanes) {
                lane.setId(null);
                for (Vehicle vehicle:lane.getVehicles()){
                    vehicle.setId(null);
                }
            }
        }
        List<Intersection> persistedIntersections = intersectionRepository.saveAll(intersections);
        return persistedIntersections;

    }

    @Override
    public List<Intersection> getVolumeByIntersectionIdDateAndLane(String intersectionId, String date, Integer laneId) {
        Date date1= null;
        try {
            date1= new SimpleDateFormat("ddMMyyyy").parse(date);
            System.out.println("Date" + date1.toString());
        } catch (Exception e) {
            System.out.println("Error occurred while date conversion");
        }

        List<Intersection> intersections= intersectionRepository.findAllIntersectionsByIntersectionIdAndDate(intersectionId,date1, laneId);
        return intersections;
//                intersectionRepository.findAll();
//        List<Intersection> newIntersectionList= new ArrayList<Intersection>();;
//        for (Intersection intersection:intersections) {
//            if (intersection.getIntersectionId().equals(intersectionId)){
//
//            }
//        }
//        return  newIntersectionList;
    }
}



