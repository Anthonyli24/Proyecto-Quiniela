package org.example.quinielasproyecto.logic.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class PartidoResponse {

    private int partidoId;
    private String equipoLocal;
    private String equipoVisitante;
    private String estadoPartido;
    private LocalDate fechaPartido;
    private LocalTime horaPartido;
    private int golesLocal;
    private int golesVisitante;

    public PartidoResponse() {
    }

    public PartidoResponse(int partidoId,
                           String equipoLocal,
                           String equipoVisitante,
                           String estadoPartido,
                           LocalDate fechaPartido,
                           LocalTime horaPartido,
                           int golesLocal,
                           int golesVisitante) {
        this.partidoId = partidoId;
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.estadoPartido = estadoPartido;
        this.fechaPartido = fechaPartido;
        this.horaPartido = horaPartido;
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
    }

    public int getPartidoId() {
        return partidoId;
    }

    public void setPartidoId(int partidoId) {
        this.partidoId = partidoId;
    }

    public String getEquipoLocal() {return equipoLocal;}

    public void setEquipoLocal(String equipoLocal) { this.equipoLocal = equipoLocal;}

    public String getEquipoVisitante() {return equipoVisitante;}

    public void setEquipoVisitante(String equipoVisitante) { this.equipoVisitante = equipoVisitante;}

    public String getEstadoPartido() {
        return estadoPartido;
    }

    public void setEstadoPartido(String estadoPartido) {
        this.estadoPartido = estadoPartido;
    }

    public LocalDate getFechaPartido() {
        return fechaPartido;
    }

    public void setFechaPartido(LocalDate fechaPartido) {
        this.fechaPartido = fechaPartido;
    }

    public LocalTime getHoraPartido() {
        return horaPartido;
    }

    public void setHoraPartido(LocalTime horaPartido) {
        this.horaPartido = horaPartido;
    }

    public int getGolesLocal() {
        return golesLocal;
    }

    public void setGolesLocal(int golesLocal) {
        this.golesLocal = golesLocal;
    }

    public int getGolesVisitante() {
        return golesVisitante;
    }

    public void setGolesVisitante(int golesVisitante) {
        this.golesVisitante = golesVisitante;
    }
}
