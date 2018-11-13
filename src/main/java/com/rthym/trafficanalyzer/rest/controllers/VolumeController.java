package com.rthym.trafficanalyzer.rest.controllers;

import com.rthym.trafficanalyzer.model.Intersection;
import com.rthym.trafficanalyzer.services.VolumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/volume")
public class VolumeController {

    @Autowired
    private VolumeService volumeService;

    @RequestMapping( method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Intersection>> getVolumeByIntersectionIdAndDate() {
       return new ResponseEntity(volumeService.getVolumeByIntersectionIdAndDate(), HttpStatus.OK);

    }

    @RequestMapping(value = "/intersection/{intersectionId}/date/{date}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<Intersection>>  syncVolumeByIntersectionIdAndDate(@PathVariable("intersectionId") String intersectionId, @PathVariable("date") String date) {

        return new ResponseEntity(volumeService.syncVolumeByIntersectionIdAndDate(intersectionId,date), HttpStatus.OK);

    }

    @RequestMapping(value = "/intersection/{intersectionId}/date/{date}/lane/{laneId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Intersection>> getVolumeByIntersectionIdDateAndLane(@PathVariable("intersectionId") String intersectionId, @PathVariable("date") String date,@PathVariable("laneId") Integer laneId) {
        return new ResponseEntity(volumeService.getVolumeByIntersectionIdDateAndLane(intersectionId,date,laneId), HttpStatus.OK);

    }


}
