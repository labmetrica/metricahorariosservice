package com.metrica.formacion.metricahorariosservice.exceptionHandler;

import com.metrica.formacion.metricahorariosservice.service.usuariosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Component
public class RestTemplateErrorHandler implements ResponseErrorHandler {


    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return (response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR ||
                response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {

        if(response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR||
                response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR){

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(response.getBody()))){

                throw new CustomErrorResponse(usuariosService.class,reader.lines().collect(Collectors.joining()), "Internal server error",response.getStatusCode());
            }

        }
    }


}
