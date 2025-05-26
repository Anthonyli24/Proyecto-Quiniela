package org.example.quinielasproyecto.logic;

import jakarta.persistence.*;

@Entity
@Table(name = "Torneo_Equipo")
public class TorneoEquipo {
    @EmbeddedId
    private TorneoEquipoId id;

    @MapsId("torneoId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "torneo_id", nullable = false)
    private Torneo torneo;

    @MapsId("equipoId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "equipo_id", nullable = false)
    private Equipo equipo;

    public TorneoEquipoId getId() {
        return id;
    }

    public void setId(TorneoEquipoId id) {
        this.id = id;
    }

    public Torneo getTorneo() {
        return torneo;
    }

    public void setTorneo(Torneo torneo) {
        this.torneo = torneo;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

}