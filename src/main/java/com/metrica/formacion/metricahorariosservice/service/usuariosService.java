package com.metrica.formacion.metricahorariosservice.service;

import com.metrica.formacion.metricahorariosservice.dto.usuariosDTO;
import com.metrica.formacion.metricahorariosservice.entity.usuarios;

import java.util.List;

public interface usuariosService {

    void borrarUsuario(Integer id);

    void guardarUsuario(usuariosDTO usuariosDTO);

    List<usuariosDTO> listarUsuarios();

    void actualizarUsuarios(usuarios usuarios);

    usuariosDTO buscarPorID(Integer id);

    List<usuariosDTO> buscarPorNomre(String nombre);
}
