package com.metrica.formacion.metricahorariosservice.config;

import com.metrica.formacion.metricahorariosservice.exceptionHandler.RestTemplateErrorHandler;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Configuration
@EnableEurekaClient
@EnableCircuitBreaker
@ComponentScan("com.metrica.formacion.metricahorariosservice")
@EnableCaching
public class SpringConfigurationFile {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {

        return restTemplateBuilder.errorHandler(new RestTemplateErrorHandler()).build();
    }

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(
                new ConcurrentMapCache("usuarios"),
                new ConcurrentMapCache("horarios"),
                new ConcurrentMapCache("buscarGrupoorNombre"),
                new ConcurrentMapCache("buscarUsuarioPorGrupo"),
                new ConcurrentMapCache("buscarGrupoPorId"),
                new ConcurrentMapCache("buscarUsuarioPorNombre"),
                new ConcurrentMapCache("grupos")));
        return cacheManager;
    }
}
