package com.example.appfinalpdmsqlite.ui.Modelo;

public class Trabajo {
    String nombre;
    String descripcion;
    String tamaño;
    String peso;
    String DNI;

    public Trabajo(String nombre, String descripcion, String tamaño, String peso, String DNI) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tamaño = tamaño;
        this.peso = peso;
        this.DNI = DNI;
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

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }



}
