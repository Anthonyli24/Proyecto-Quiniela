package org.example.quinielasproyecto.logic.service;

import org.example.quinielasproyecto.data.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    public List<Map<String, Object>> obtenerEquipos() {
        return equipoRepository.obtenerEquipos();
    }
}
