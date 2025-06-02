package org.example.quinielasproyecto.logic.Entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "Premio_Quiniela")
public class PremioQuiniela {
    @EmbeddedId
    private PremioQuinielaId id;

    @MapsId("quinielaId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "quiniela_id", nullable = false)
    private Quiniela quiniela;

    @MapsId("premioId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "premio_id", nullable = false)
    private Premio premio;

    public PremioQuinielaId getId() {
        return id;
    }

    public void setId(PremioQuinielaId id) {
        this.id = id;
    }

    public Quiniela getQuiniela() {
        return quiniela;
    }

    public void setQuiniela(Quiniela quiniela) {
        this.quiniela = quiniela;
    }

    public Premio getPremio() {
        return premio;
    }

    public void setPremio(Premio premio) {
        this.premio = premio;
    }

}