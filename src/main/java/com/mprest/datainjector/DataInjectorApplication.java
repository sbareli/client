package com.mprest.datainjector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@SpringBootApplication
public class DataInjectorApplication {

	static Logger logger = LoggerFactory.getLogger(DataInjectorApplication.class);
	static String shapeResourceUrl = "http://localhost:8080/shapes/rightTriangles";

	public static void main(String[] args) {
		SpringApplication.run(DataInjectorApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {

		/*
			CommandLineRunner is a functional interface with one abstract method
			void run(String... args);
		 */

		return args -> {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

            java.net.URI uri = new URI(shapeResourceUrl);

            long waitTime;
            for (int i=0; i<10000; ++i)
            {
			    Shape shape = new Shape();

                HttpEntity<Shape> request = new HttpEntity<>(shape, headers);

                restTemplate.postForLocation(uri, request);

				waitTime = (long) ((Math.random()+1)*100);

                Thread.sleep(waitTime);

                logger.info("Inserting shape: after " + waitTime + "ms");
            }

		};
	}

}
