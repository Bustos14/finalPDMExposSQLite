package com.example.appfinalpdmsqlite.ui.Modelo;

public class Comentario {

    String idExposicion, nombreTrabajo, comentario;

    public Comentario(String idExposicion, String nombreTrabajo, String comentario) {
        this.idExposicion = idExposicion;
        this.nombreTrabajo = nombreTrabajo;
        this.comentario = comentario;
    }

    public String getIdExposicion() {
        return idExposicion;
    }

    public void setIdExposicion(String idExposicion) {
        this.idExposicion = idExposicion;
    }

    public String getNombreTrabajo() {
        return nombreTrabajo;
    }

    public void setNombreTrabajo(String nombreTrabajo) {
        this.nombreTrabajo = nombreTrabajo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
