package com.rthym.trafficanalyzer.services;

import com.rthym.trafficanalyzer.model.Intersection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Component
@Service
@CacheConfig(cacheNames = "externalApiData")
public class IntersectionApiCallerServiceImpl {

    @Autowired
    RefDataService refDataService;

    @Autowired
    VolumeService volumeService;

    private  static IntersectionApiCallerService intersectionApiCallerService;
    private  static  final Logger LOGGER = LoggerFactory.getLogger(IntersectionApiCallerServiceImpl.class);
    static {
        try {
            intersectionApiCallerService = new IntersectionApiCallerService();
        }catch (Exception e){
            LOGGER.error("Couldn't connect to rest api for fetching intersection data from external cloud");
        }
    }

    public List<Intersection> syncDataFromExternalApi(String intersectionId, String date ) {
        TimeZone tz = TimeZone.getTimeZone("GMT");

        Calendar tzCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

        Date date1= tzCal.getTime();
        try {
              date1= new SimpleDateFormat("ddMMyyyy").parse(date);
            System.out.println("Date" + date1.toString());
        } catch (Exception e) {
            System.out.println("Error occurred while date conversion");
        }
            List<Intersection> intersections=getVolumeByIntersectionIdAndDateFromExternalApi(intersectionId,date);
           return volumeService.insertInToVolumeByIntersectionIdAndDate(intersectionId, intersections, date1);
    }

    private List<Intersection> getVolumeByIntersectionIdAndDateFromExternalApi(String intersectionId, String date) {

        ResponseEntity<List<Intersection>> volumeResponseEntity = intersectionApiCallerService.getIntersectionByIntersectionIdAndDate(intersectionId, date);//getVolumeByIntersectionIdAndDate(intersectionId,date);
        try {
            Date date1 = new SimpleDateFormat("ddMMyyyy").parse(date);
            System.out.println("Date" + date1.toString());
        } catch (Exception e) {
            System.out.println("Error occurred while date conversion");
        }
        return volumeResponseEntity.getBody();
    }

    @Scheduled(fixedRate = 555000)
    public void syncDataFromExternalApi() {

        List<String> intersectionIds=refDataService.getAllDistinctIntersectionIds();
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        String date= dateFormat.format(new Date());

        Date date1= null;
        try {
            date1= new SimpleDateFormat("ddMMyyyy").parse(date);
            System.out.println("Date:  " + date1.toString());
        } catch (Exception e) {
            System.out.println("Error occurred while date conversion");
        }

        for (String intersectionId : intersectionIds){
            System.out.println("IntersectionId: "+intersectionId +"Date: "+date);
            List<Intersection> intersections=getVolumeByIntersectionIdAndDateFromExternalApi(intersectionId,date);
            volumeService.insertInToVolumeByIntersectionIdAndDate(intersectionId,intersections, date1);
        }
    }

}
