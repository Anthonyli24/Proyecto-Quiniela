package org.example.quinielasproyecto.logic.dto;

public class RankingDTO {
    private String usuario;
    private int puntaje;

    public RankingDTO() {}

    public RankingDTO(String usuario, int puntaje) {
        this.usuario = usuario;
        this.puntaje = puntaje;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
}

