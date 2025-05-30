package org.example.quinielasproyecto.logic.dto;

import java.time.LocalDate;

public class QuinielaResponse {

    private int quinielaId;
    private String nombre;
    private String descripcion;
    private String reglas;
    private LocalDate fechaInicioInscripciones;
    private LocalDate fechaFinalInscripciones;
    private String estado;
    private String modalidad;
    private String tipoPuntuacion;
    private int torneoId;

    public QuinielaResponse() {
    }

    public QuinielaResponse(int quinielaId,
                            String nombre,
                            String descripcion,
                            String reglas,
                            LocalDate fechaInicioInscripciones,
                            LocalDate fechaFinalInscripciones,
                            String estado,
                            String modalidad,
                            String tipoPuntuacion,
                            int torneoId) {
        this.quinielaId = quinielaId;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.reglas = reglas;
        this.fechaInicioInscripciones = fechaInicioInscripciones;
        this.fechaFinalInscripciones = fechaFinalInscripciones;
        this.estado = estado;
        this.modalidad = modalidad;
        this.tipoPuntuacion = tipoPuntuacion;
        this.torneoId = torneoId;
    }

    public int getQuinielaId() {
        return quinielaId;
    }

    public void setQuinielaId(int quinielaId) {
        this.quinielaId = quinielaId;
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

    public String getReglas() {
        return reglas;
    }

    public void setReglas(String reglas) {
        this.reglas = reglas;
    }

    public LocalDate getFechaInicioInscripciones() {
        return fechaInicioInscripciones;
    }

    public void setFechaInicioInscripciones(LocalDate fechaInicioInscripciones) {
        this.fechaInicioInscripciones = fechaInicioInscripciones;
    }

    public LocalDate getFechaFinalInscripciones() {
        return fechaFinalInscripciones;
    }

    public void setFechaFinalInscripciones(LocalDate fechaFinalInscripciones) {
        this.fechaFinalInscripciones = fechaFinalInscripciones;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getTipoPuntuacion() {
        return tipoPuntuacion;
    }

    public void setTipoPuntuacion(String tipoPuntuacion) {
        this.tipoPuntuacion = tipoPuntuacion;
    }

    public int getTorneoId() {
        return torneoId;
    }

    public void setTorneoId(int torneoId) {
        this.torneoId = torneoId;
    }
}
