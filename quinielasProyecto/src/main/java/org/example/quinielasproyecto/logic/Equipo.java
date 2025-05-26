package org.example.quinielasproyecto.logic;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Equipo {
    @Id
    @Column(name = "equipo_id", nullable = false)
    private Integer id;

    @Column(name = "nombre_equipo", nullable = false, length = 100)
    private String nombreEquipo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

}