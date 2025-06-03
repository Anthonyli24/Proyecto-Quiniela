package org.example.quinielasproyecto.logic.service;

import org.example.quinielasproyecto.data.TorneoJPARepository;
import org.example.quinielasproyecto.data.TorneoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TorneoService {

    @Autowired
    private TorneoJPARepository torneoJPARepository;

    @Autowired
    private TorneoRepository torneoRepository;

    public void crearTorneo(String nombre, Date fechaInicio, Date fechaCierre, String resultadoFinal) {
        torneoJPARepository.crearTorneo(nombre, fechaInicio, fechaCierre, resultadoFinal);
    }

    public List<Map<String, Object>> obtenerTorneos() {
        List<Object[]> filas = torneoRepository.obtenerTorneos();
        List<Map<String, Object>> resultado = new ArrayList<>();

        for (Object[] fila : filas) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", ((Number) fila[0]).intValue());
            map.put("nombre", fila[1].toString());
            resultado.add(map);
        }

        return resultado;
    }
}
