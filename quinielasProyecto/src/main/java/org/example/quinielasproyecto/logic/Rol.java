package org.example.quinielasproyecto.logic;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Rol {
    @Id
    @Column(name = "rol_id", nullable = false)
    private Integer id;

    @Column(name = "rol", nullable = false, length = 50)
    private String rol;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

}