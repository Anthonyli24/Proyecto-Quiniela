package org.example.quinielasproyecto.logic.service;

import org.example.quinielasproyecto.data.PartidoRepository;
import org.example.quinielasproyecto.logic.dto.PartidoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartidoService {
    @Autowired
    private PartidoRepository partidoRepository;

    public String registrarPartido(PartidoRequest request) {
        return partidoRepository.insertarPartido(request);
    }
}
