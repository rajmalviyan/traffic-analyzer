package com.rthym.trafficanalyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories
@EnableCaching
public class TrafficAnalyzerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrafficAnalyzerApplication.class, args);
	}
}
