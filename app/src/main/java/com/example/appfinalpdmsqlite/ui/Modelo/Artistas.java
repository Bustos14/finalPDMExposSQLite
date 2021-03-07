package com.example.appfinalpdmsqlite.ui.Modelo;

import java.io.Serializable;

public class Artistas implements Serializable {
    private String dniPasaporte;
    private String nombre;
    private String direccion;
    private String poblacion;
    private String provincia;
    private String pais;
    private Integer movilTrabajo;
    private Integer movilPersonal;
    private Integer telefonoFijo;
    private String email;
    private String webBlog;
    private String fNacimiento;

    public Artistas(String dniPasaporte, String nombre, String direccion, String poblacion, String provincia, String pais, String email, String webBlog, String fNacimiento, Integer movilTrabajo, Integer movilPersonal, Integer telefonoFijo) {
        this.dniPasaporte = dniPasaporte;
        this.nombre = nombre;
        this.direccion = direccion;
        this.poblacion = poblacion;
        this.provincia = provincia;
        this.pais = pais;
        this.movilTrabajo = movilTrabajo;
        this.movilPersonal = movilPersonal;
        this.telefonoFijo = telefonoFijo;
        this.email = email;
        this.webBlog = webBlog;
        this.fNacimiento = fNacimiento;
    }


    public String getDniPasaporte() {
        return dniPasaporte;
    }

    public void setDniPasaporte(String dniPasaporte) {
        this.dniPasaporte = dniPasaporte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Integer getMovilTrabajo() {
        return movilTrabajo;
    }

    public void setMovilTrabajo(Integer movilTrabajo) {
        this.movilTrabajo = movilTrabajo;
    }

    public Integer getMovilPersonal() {
        return movilPersonal;
    }

    public void setMovilPersonal(Integer movilPersonal) {
        this.movilPersonal = movilPersonal;
    }

    public Integer getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(Integer telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebBlog() {
        return webBlog;
    }

    public void setWebBlog(String webBlog) {
        this.webBlog = webBlog;
    }

    public String getfNacimiento() {
        return fNacimiento;
    }

    public void setfNacimiento(String fNacimiento) {
        this.fNacimiento = fNacimiento;
    }
}
