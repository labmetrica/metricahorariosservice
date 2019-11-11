package com.metrica.formacion.metricahorariosservice.dto;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class gruposDTO {

    private int id;
    private LocalTime nombre;
    private int huecos;
    private List<usuariosDTO> listaUsuarios = new ArrayList<>(12);

    public gruposDTO(){
        //listaUsuarios = Arrays.asList(new usuariosDTO[12]);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalTime getNombre() {
        return nombre;
    }

    public void setNombre(LocalTime nombre) {
        this.nombre = nombre;
    }

    public int getHuecos() {
        return huecos;
    }

    public void setHuecos(int huecos) {
        this.huecos = huecos;
    }

    public List<usuariosDTO> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<usuariosDTO> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public void quitarUsuario(usuariosDTO usuarioDTO){

        for(usuariosDTO usuario:listaUsuarios) {

            if(usuario.getId() == usuarioDTO.getId()){

                listaUsuarios.remove(usuario);
                huecos ++;
            }
        }
    }

    public void quitarUsuario(Integer id){

        for(usuariosDTO usuario:listaUsuarios) {

            if(usuario.getId() == id){

                listaUsuarios.remove(usuario);
                huecos ++;
            }
        }
    }

    public void agregarUsuario(usuariosDTO usuarioDTO) {

        usuarioDTO.setGrupo(id);

        if(listaUsuarios == null){

            listaUsuarios = new ArrayList<>(12);
            listaUsuarios.add(usuarioDTO);
            huecos--;

        }

        listaUsuarios.add(usuarioDTO);
        huecos--;
    }
}
