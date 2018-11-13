package com.rthym.trafficanalyzer.services;

import com.rthym.trafficanalyzer.model.Intersection;
import com.rthym.trafficanalyzer.utilities.PropertyLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class IntersectionApiCallerService extends AbstractRestCaller{
    public static final String REQUUEST_BODY="";
    private final String GET_TRANSACTIONS_URL_TEMPLATE;

    private static final Logger LOGGER= LoggerFactory.getLogger(IntersectionApiCallerService.class);

    public IntersectionApiCallerService() throws  Exception {
        super(PropertyLoader.getProperty("rest.api.endpoint"));
        this.keyStoreFileLocation= PropertyLoader.getProperty("rest.api.ssl.keystore.location");
        this.keyStoreFilePassword= PropertyLoader.getProperty("rest.api.ssl.keystore.password");
        GET_TRANSACTIONS_URL_TEMPLATE= endpoint+"intersection/{intersectionId}/date/{date}";

        configureSSLIfNeeded();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    }

    private void configureSSLIfNeeded() throws  Exception {
        if(endpoint.toLowerCase().startsWith("https")){
            configureSSL();
        }else{
            LOGGER.info("Skipping SSL configuration");
        }
    }

    public ResponseEntity<List<Intersection>> getVolumeByIntersectionIdAndDate(String intersectionId, String date){
        HttpEntity<String> entity= new HttpEntity(REQUUEST_BODY, headers);
        return this.restTemplate.exchange(GET_TRANSACTIONS_URL_TEMPLATE, HttpMethod.GET, entity, new ParameterizedTypeReference<List<Intersection>>() {},  intersectionId, date);
    }


    public ResponseEntity<List<Intersection>> getIntersectionByIntersectionIdAndDate(String intersectionId, String date){
        HttpEntity<String> entity= new HttpEntity(REQUUEST_BODY, headers);
        return this.restTemplate.exchange(GET_TRANSACTIONS_URL_TEMPLATE, HttpMethod.GET, entity, new ParameterizedTypeReference<List<Intersection>>() {}, intersectionId, date);
    }

    public  List<Intersection> getVolumeByIntersectionAndDate(String intersectionId, String date){
//        ResponseEntity<Volume> responseEntity= getVolumeByIntersectionIdAndDate(intersectionId,date);
        ResponseEntity<List<Intersection>> responseEntity= getIntersectionByIntersectionIdAndDate(intersectionId,date);
        if (HttpStatus.OK.equals(responseEntity.getStatusCode())){
//            return convertResponse(responseEntity);
            return responseEntity.getBody();
        }
//        else{
//            return  Collections.emptyList();
//        }
        return null;
    }

//    private List<Intersection> convertResponse(ResponseEntity<List<Intersection>> responseEntity) {
//        if (responseEntity==null||responseEntity.getBody()==null||responseEntity.getBody().getIntersections()==null){
//            return Collections.emptyList();
//        }
//        return new ArrayList<>(responseEntity.getBody().getIntersections());
//    }
}
