package com.metrica.formacion.metricahorariosservice.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class grupos {

    private int id;
    private LocalTime nombre;
    private int huecos = 12;
    private LocalDateTime createdAT;
    private LocalDateTime ultimaModificacion;

    public grupos() {

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

    public void setHuecosmas1() {

        if (huecos < 12) {
            huecos++;
        }
    }

    public void setHuecosmenos1() {

        if (huecos > 0) {
            huecos--;
        }
    }

    public LocalDateTime getCreatedAT() {
        return createdAT;
    }

    public void setCreatedAT() {
        this.createdAT = LocalDateTime.now();
    }

    public LocalDateTime getUltimaModificacion() {
        return ultimaModificacion;
    }

    public void setUltimaModificacion() {
        this.ultimaModificacion = LocalDateTime.now();
    }
}
