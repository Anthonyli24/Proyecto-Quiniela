package org.example.quinielasproyecto.logic;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PremioQuinielaId implements Serializable {
    private static final long serialVersionUID = -486877205046615351L;
    @Column(name = "quiniela_id", nullable = false)
    private Integer quinielaId;

    @Column(name = "premio_id", nullable = false)
    private Integer premioId;

    public Integer getQuinielaId() {
        return quinielaId;
    }

    public void setQuinielaId(Integer quinielaId) {
        this.quinielaId = quinielaId;
    }

    public Integer getPremioId() {
        return premioId;
    }

    public void setPremioId(Integer premioId) {
        this.premioId = premioId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PremioQuinielaId entity = (PremioQuinielaId) o;
        return Objects.equals(this.quinielaId, entity.quinielaId) &&
                Objects.equals(this.premioId, entity.premioId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quinielaId, premioId);
    }

}