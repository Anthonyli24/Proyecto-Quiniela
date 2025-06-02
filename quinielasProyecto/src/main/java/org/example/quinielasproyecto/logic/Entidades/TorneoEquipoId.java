package org.example.quinielasproyecto.logic.Entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TorneoEquipoId implements Serializable {
    private static final long serialVersionUID = -1056917201591435358L;
    @Column(name = "torneo_id", nullable = false)
    private Integer torneoId;

    @Column(name = "equipo_id", nullable = false)
    private Integer equipoId;

    public Integer getTorneoId() {
        return torneoId;
    }

    public void setTorneoId(Integer torneoId) {
        this.torneoId = torneoId;
    }

    public Integer getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(Integer equipoId) {
        this.equipoId = equipoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TorneoEquipoId entity = (TorneoEquipoId) o;
        return Objects.equals(this.equipoId, entity.equipoId) &&
                Objects.equals(this.torneoId, entity.torneoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(equipoId, torneoId);
    }

}