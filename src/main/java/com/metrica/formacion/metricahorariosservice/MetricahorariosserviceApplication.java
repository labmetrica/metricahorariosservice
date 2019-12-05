package com.metrica.formacion.metricahorariosservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;


@SpringBootApplication
@EnableCircuitBreaker
public class MetricahorariosserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetricahorariosserviceApplication.class, args);
	}

}
