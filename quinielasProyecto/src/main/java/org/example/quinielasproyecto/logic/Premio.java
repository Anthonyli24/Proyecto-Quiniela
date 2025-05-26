package org.example.quinielasproyecto.logic;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Premio {
    @Id
    @Column(name = "premio_id", nullable = false)
    private Integer id;

    @Column(name = "nombre_premio", nullable = false, length = 100)
    private String nombrePremio;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombrePremio() {
        return nombrePremio;
    }

    public void setNombrePremio(String nombrePremio) {
        this.nombrePremio = nombrePremio;
    }

}