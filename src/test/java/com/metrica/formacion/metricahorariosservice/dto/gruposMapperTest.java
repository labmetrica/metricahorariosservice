package com.metrica.formacion.metricahorariosservice.dto;

import com.metrica.formacion.metricahorariosservice.config.SpringConfigurationFile;
import com.metrica.formacion.metricahorariosservice.entity.grupos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = SpringConfigurationFile.class)
class gruposMapperTest {

    @Autowired
    private gruposMapper gruposMapper;

    @Test
    void testToUsuariosDTTO(){

        grupos grupos = new grupos();
        grupos.setId(12);
        grupos.setNombre(LocalTime.of(13,00,00));

        gruposDTO gruposDTO = gruposMapper.togruposDTO(grupos);

        Assertions.assertEquals(grupos.getId(),gruposDTO.getId());
        Assertions.assertEquals(12,gruposDTO.getHuecos());
        Assertions.assertEquals("13:00",gruposDTO.getNombre().toString());
    }
}