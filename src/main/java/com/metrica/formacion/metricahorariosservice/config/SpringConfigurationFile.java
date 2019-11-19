package com.metrica.formacion.metricahorariosservice.config;

import com.metrica.formacion.metricahorariosservice.exceptionHandler.RestTemplateErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableAutoConfiguration
@EnableEurekaClient
@ComponentScan("com.metrica.formacion.metricahorariosservice")
public class SpringConfigurationFile {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {

        return restTemplateBuilder.errorHandler(new RestTemplateErrorHandler()).build();
    }
}
