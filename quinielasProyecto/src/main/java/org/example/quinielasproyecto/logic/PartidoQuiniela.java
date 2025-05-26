package org.example.quinielasproyecto.logic;

import jakarta.persistence.*;

@Entity
@Table(name = "Partido_Quiniela")
public class PartidoQuiniela {
    @EmbeddedId
    private PartidoQuinielaId id;

    @MapsId("partidoId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "partido_id", nullable = false)
    private Partido partido;

    @MapsId("quinielaId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "quiniela_id", nullable = false)
    private Quiniela quiniela;

    public PartidoQuinielaId getId() {
        return id;
    }

    public void setId(PartidoQuinielaId id) {
        this.id = id;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public Quiniela getQuiniela() {
        return quiniela;
    }

    public void setQuiniela(Quiniela quiniela) {
        this.quiniela = quiniela;
    }

}