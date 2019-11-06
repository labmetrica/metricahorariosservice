package com.metrica.formacion.metricahorariosservice.dto;

import java.time.LocalDateTime;

public class usuariosDTO {

    private int id;
    private String nombre;
    private String apellido;
    private int grupo;
    private String email;

    //

    public usuariosDTO() {
    }

    //

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getGrupo() {
        return grupo;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "usuariosDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", grupo=" + grupo +
                ", email='" + email + '\'' +
                '}';
    }
}
