package org.example.quinielasproyecto.logic;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PartidoQuinielaId implements Serializable {
    private static final long serialVersionUID = 8264293733617640173L;
    @Column(name = "partido_id", nullable = false)
    private Integer partidoId;

    @Column(name = "quiniela_id", nullable = false)
    private Integer quinielaId;

    public Integer getPartidoId() {
        return partidoId;
    }

    public void setPartidoId(Integer partidoId) {
        this.partidoId = partidoId;
    }

    public Integer getQuinielaId() {
        return quinielaId;
    }

    public void setQuinielaId(Integer quinielaId) {
        this.quinielaId = quinielaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PartidoQuinielaId entity = (PartidoQuinielaId) o;
        return Objects.equals(this.quinielaId, entity.quinielaId) &&
                Objects.equals(this.partidoId, entity.partidoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quinielaId, partidoId);
    }

}