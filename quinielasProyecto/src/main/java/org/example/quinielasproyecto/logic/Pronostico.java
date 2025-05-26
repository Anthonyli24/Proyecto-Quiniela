package org.example.quinielasproyecto.logic;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Pronostico {
    @Id
    @Column(name = "pronostico_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "goles_visita", nullable = false)
    private Integer golesVisita;

    @Column(name = "goles_local", nullable = false)
    private Integer golesLocal;

    @Column(name = "fecha_pronostico", nullable = false)
    private LocalDate fechaPronostico;

    @Column(name = "hora_pronostico", nullable = false)
    private LocalTime horaPronostico;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "partido_id", nullable = false)
    private Partido partido;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "quiniela_id", nullable = false)
    private Quiniela quiniela;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getGolesVisita() {
        return golesVisita;
    }

    public void setGolesVisita(Integer golesVisita) {
        this.golesVisita = golesVisita;
    }

    public Integer getGolesLocal() {
        return golesLocal;
    }

    public void setGolesLocal(Integer golesLocal) {
        this.golesLocal = golesLocal;
    }

    public LocalDate getFechaPronostico() {
        return fechaPronostico;
    }

    public void setFechaPronostico(LocalDate fechaPronostico) {
        this.fechaPronostico = fechaPronostico;
    }

    public LocalTime getHoraPronostico() {
        return horaPronostico;
    }

    public void setHoraPronostico(LocalTime horaPronostico) {
        this.horaPronostico = horaPronostico;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public Quiniela getQuiniela() {
        return quiniela;
    }

    public void setQuiniela(Quiniela quiniela) {
        this.quiniela = quiniela;
    }

}