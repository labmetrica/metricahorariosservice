package com.metrica.formacion.metricahorariosservice.service;

import com.metrica.formacion.metricahorariosservice.dto.usuariosDTO;
import com.metrica.formacion.metricahorariosservice.entity.usuarios;
import org.springframework.cloud.client.loadbalancer.reactive.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface usuariosService {

    void borrarUsuario(Integer id);

    ResponseEntity<usuarios> guardarUsuario(usuariosDTO usuariosDTO);

    List<usuariosDTO> listarUsuarios();

    ResponseEntity<usuarios> actualizarUsuarios(usuariosDTO usuarios);

    usuariosDTO buscarPorID(Integer id);

    List<usuariosDTO> buscarPorGrupo(Integer id);

    List<usuariosDTO> buscarPorNombre(String nombre);

    List<usuariosDTO> buscarPorApellido(String apellido);

    List<usuariosDTO> buscarPorNombreoApellido(String nombre, String apellido);
}
