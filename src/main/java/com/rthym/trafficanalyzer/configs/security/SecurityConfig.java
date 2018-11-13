package com.rthym.trafficanalyzer.configs.security;

import org.h2.server.web.WebServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("h2-console", "h2-console/**", "h2/**").permitAll()
                .mvcMatchers("/volume/intersection/{intersectionId}/date/{date}").permitAll()
                .mvcMatchers("/volume/intersection/{intersectionId}/date/{date}/lane/{laneId}").permitAll()
                .mvcMatchers("/volume").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf()
                .disable();

        http.headers().frameOptions().disable();
    }

    @Bean
    ServletRegistrationBean h2servletRegistration(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }

}
