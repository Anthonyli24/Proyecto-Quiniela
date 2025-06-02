package org.example.quinielasproyecto.logic;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Quiniela {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiniela_id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Lob
    @Column(name = "descripcion")
    private String descripcion;

    @Lob
    @Column(name = "reglas")
    private String reglas;

    @Column(name = "fecha_inicio_inscripciones", nullable = false)
    private LocalDate fechaInicioInscripciones;

    @Column(name = "fecha_final_inscripciones", nullable = false)
    private LocalDate fechaFinalInscripciones;

    @Column(name = "estado", nullable = false, length = 50)
    private String estado;

    @Column(name = "modalidad", nullable = false, length = 50)
    private String modalidad;

    @Column(name = "tipo_puntuacion", nullable = false, length = 50)
    private String tipoPuntuacion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "torneo_id", nullable = false)
    private Torneo torneo;

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

    public Torneo getTorneo() {
        return torneo;
    }

    public void setTorneo(Torneo torneo) {
        this.torneo = torneo;
    }

}