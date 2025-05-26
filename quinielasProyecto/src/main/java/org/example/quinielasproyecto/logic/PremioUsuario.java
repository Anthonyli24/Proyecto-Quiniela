package org.example.quinielasproyecto.logic;

import jakarta.persistence.*;

@Entity
@Table(name = "Premio_Usuario")
public class PremioUsuario {
    @EmbeddedId
    private PremioUsuarioId id;

    @MapsId("usuarioId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @MapsId("premioId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "premio_id", nullable = false)
    private Premio premio;

    public PremioUsuarioId getId() {
        return id;
    }

    public void setId(PremioUsuarioId id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Premio getPremio() {
        return premio;
    }

    public void setPremio(Premio premio) {
        this.premio = premio;
    }

}