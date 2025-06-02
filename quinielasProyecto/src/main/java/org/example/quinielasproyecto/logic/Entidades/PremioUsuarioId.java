package org.example.quinielasproyecto.logic.Entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PremioUsuarioId implements Serializable {
    private static final long serialVersionUID = -2430532468519337511L;
    @Column(name = "usuario_id", nullable = false)
    private Integer usuarioId;

    @Column(name = "premio_id", nullable = false)
    private Integer premioId;

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
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
        PremioUsuarioId entity = (PremioUsuarioId) o;
        return Objects.equals(this.usuarioId, entity.usuarioId) &&
                Objects.equals(this.premioId, entity.premioId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioId, premioId);
    }

}