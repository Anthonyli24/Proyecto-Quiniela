package org.example.quinielasproyecto.logic;

import jakarta.persistence.*;

@Entity
public class Inscripcion {
    @Id
    @Column(name = "inscripcion_Id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "quiniela_id", nullable = false)
    private Quiniela quiniela;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "puntaje", nullable = false)
    private Integer puntaje;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Quiniela getQuiniela() {
        return quiniela;
    }

    public void setQuiniela(Quiniela quiniela) {
        this.quiniela = quiniela;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
    }

}