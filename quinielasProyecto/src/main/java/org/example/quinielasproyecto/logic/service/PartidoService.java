package org.example.quinielasproyecto.logic.service;

import org.example.quinielasproyecto.data.PartidoRepository;
import org.example.quinielasproyecto.logic.dto.PartidoRequest;
import org.example.quinielasproyecto.logic.dto.PartidoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartidoService {
    @Autowired
    private PartidoRepository partidoRepository;

    public String registrarPartido(PartidoRequest request) {
        return partidoRepository.insertarPartido(request);
    }

    public List<PartidoResponse> obtenerPartidos() {
        return partidoRepository.obtenerPartidos();
    }
}
