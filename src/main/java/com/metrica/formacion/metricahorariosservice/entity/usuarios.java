package com.metrica.formacion.metricahorariosservice.entity;

import java.time.LocalDateTime;

public class usuarios {

    private int id;
    private String nombre;
    private String apellido;
    private int grupo;
    private String email;
    private LocalDateTime createdAT;
    private boolean activo;
    private LocalDateTime ultima_modificacion;

    //Constructor

    public usuarios() {
        createdAT = LocalDateTime.now();
    }

    //Getters and Setters
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

    public LocalDateTime getCreatedAT() {
        return createdAT;
    }

    public void setCreatedAT(LocalDateTime createdAT) {
        this.createdAT = createdAT;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public LocalDateTime getUltima_modificacion() {
        return ultima_modificacion;
    }

    public void setUltima_modificacion(LocalDateTime ultima_modificacion) {
        this.ultima_modificacion = ultima_modificacion;
    }

    public void setUltima_modificacion() {

        this.ultima_modificacion = LocalDateTime.now();
    }
}
