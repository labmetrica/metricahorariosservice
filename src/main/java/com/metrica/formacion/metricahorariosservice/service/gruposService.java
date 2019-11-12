package com.metrica.formacion.metricahorariosservice.service;

import com.metrica.formacion.metricahorariosservice.dto.gruposDTO;
import com.metrica.formacion.metricahorariosservice.dto.usuariosDTO;
import com.metrica.formacion.metricahorariosservice.entity.grupos;
import com.metrica.formacion.metricahorariosservice.entity.usuarios;
import org.springframework.http.ResponseEntity;

import java.time.LocalTime;
import java.util.List;

public interface gruposService {

    void borrarGrupo(Integer id);

    ResponseEntity<grupos> guardarGrupo(gruposDTO grupos);

    List<gruposDTO> listarGrupos();

    ResponseEntity<grupos> actualizarGrupo(gruposDTO grupos);

    gruposDTO buscarPorID(Integer id);

    gruposDTO buscarPorNombre(LocalTime localTime);
}
