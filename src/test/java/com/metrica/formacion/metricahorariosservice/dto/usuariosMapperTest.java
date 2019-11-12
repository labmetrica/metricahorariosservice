package com.metrica.formacion.metricahorariosservice.dto;

import com.metrica.formacion.metricahorariosservice.config.SpringConfigurationFile;
import com.metrica.formacion.metricahorariosservice.entity.usuarios;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SpringConfigurationFile.class)
class usuariosMapperTest {

    @Autowired
    private com.metrica.formacion.metricahorariosservice.dto.usuariosMapper usuariosMapper;

    @Test
    void testToUsuariosDTTO(){

        usuarios usuarios = new usuarios();
        usuarios.setId(12);
        usuarios.setNombre("kevin");
        usuarios.setActivo(true);
        usuarios.setApellido("barquin");
        usuarios.setGrupo(4500);
        usuarios.setEmail("test");

        usuariosDTO usuariosDTO = usuariosMapper.tousuariosDTO(usuarios);

        Assertions.assertEquals("kevin",usuariosDTO.getNombre());
        Assertions.assertEquals(12,usuariosDTO.getId());
        Assertions.assertEquals("test",usuariosDTO.getEmail());
        Assertions.assertEquals("barquin",usuariosDTO.getApellido());
    }
}