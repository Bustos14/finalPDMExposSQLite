package com.example.appfinalpdmsqlite.ui.Modelo;

public class Exposicion {
    Integer id;
    String nombre;
    String descripcion;
    String fechaIni;
    String fechaFin;

    public Exposicion(Integer id, String nombre, String descripcion, String fechaIni, String fechaFin) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(String fechaIni) {
        this.fechaIni = fechaIni;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }
}
