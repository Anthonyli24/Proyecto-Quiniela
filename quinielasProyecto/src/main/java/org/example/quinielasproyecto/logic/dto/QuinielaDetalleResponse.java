package org.example.quinielasproyecto.logic.dto;

import java.util.List;

public class QuinielaDetalleResponse {

    private QuinielaResponse quiniela;
    private List<PartidoResponse> partidos;

    public QuinielaDetalleResponse() {
    }

    public QuinielaDetalleResponse(QuinielaResponse quiniela, List<PartidoResponse> partidos) {
        this.quiniela = quiniela;
        this.partidos = partidos;
    }

    public QuinielaResponse getQuiniela() {
        return quiniela;
    }

    public void setQuiniela(QuinielaResponse quiniela) {
        this.quiniela = quiniela;
    }

    public List<PartidoResponse> getPartidos() {
        return partidos;
    }

    public void setPartidos(List<PartidoResponse> partidos) {
        this.partidos = partidos;
    }
}
