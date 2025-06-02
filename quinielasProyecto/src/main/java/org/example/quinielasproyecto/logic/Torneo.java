package org.example.quinielasproyecto.logic;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Torneo {
    @Id
    @Column(name = "torneo_id", nullable = false)
    private Integer id;

    @Column(name = "torneo_nombre")
    private String torneoNombre;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_cierre", nullable = false)
    private LocalDate fechaCierre;

    @Column(name = "resultado_final")
    private String resultadoFinal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTorneoNombre() {return torneoNombre;}

    public void setTorneoNombre(String torneoNombre) {this.torneoNombre = torneoNombre;}

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(LocalDate fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public String getResultadoFinal() {
        return resultadoFinal;
    }

    public void setResultadoFinal(String resultadoFinal) {
        this.resultadoFinal = resultadoFinal;
    }

}