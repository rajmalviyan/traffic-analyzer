package com.rthym.trafficanalyzer.configs;


import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.concurrent.TimeUnit;

public  class WebMvcConfig extends WebMvcConfigurerAdapter{

    @Override
    public void  addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/,/**")
                .addResourceLocations("classpath:/public/")
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
    }

}