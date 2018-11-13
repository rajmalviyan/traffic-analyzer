package com.rthym.trafficanalyzer.services;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.security.KeyStore;

public class AbstractRestCaller {

    protected static final String JKS= "JKS";
    protected RestTemplate restTemplate;
    protected HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
    protected HttpHeaders headers = new HttpHeaders();
    protected String endpoint;
    protected String keyStoreFileLocation;
    protected String keyStoreFilePassword;
    private  static final Logger LOGGER= LoggerFactory.getLogger(AbstractRestCaller.class);

    public AbstractRestCaller(String endpoint){
        this.endpoint= endpoint;
        this.restTemplate= new RestTemplate(requestFactory);

    }

    protected  void configureSSL() throws  Exception{
        configureSSL(JKS);
    }
    protected  void configureSSL(String storeType) throws Exception{
        LOGGER.info("Trying to read SSL Keystore for {} certificate", this.getClass());
        KeyStore keyStore= KeyStore.getInstance(storeType);
        try (FileInputStream fileInputStream= new FileInputStream(keyStoreFileLocation)){
            keyStore.load(fileInputStream, keyStoreFilePassword.toCharArray());
            SSLContext sslContext= SSLContexts.custom().loadTrustMaterial(keyStore, null).build();
            SSLConnectionSocketFactory csf= new SSLConnectionSocketFactory(sslContext);
            CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
            requestFactory.setHttpClient(httpClient);
            LOGGER.info("SSL KeyStore Settings Applied");
        }

    }
}
