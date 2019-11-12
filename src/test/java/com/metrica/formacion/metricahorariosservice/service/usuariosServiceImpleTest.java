package com.metrica.formacion.metricahorariosservice.service;

import com.metrica.formacion.metricahorariosservice.config.SpringConfigurationFile;
import com.metrica.formacion.metricahorariosservice.dto.usuariosDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = SpringConfigurationFile.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class usuariosServiceImpleTest {

    @Autowired
    private com.metrica.formacion.metricahorariosservice.service.usuariosService usuariosService;

    @Test
    void listarUsuarios() {

        List<usuariosDTO> lista = usuariosService.listarUsuarios();

        Assertions.assertEquals(5,lista.size());
    }
}