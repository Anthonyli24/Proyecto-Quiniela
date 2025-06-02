package org.example.quinielasproyecto.logic.service;

import org.example.quinielasproyecto.data.InscripcionRepository;
import org.example.quinielasproyecto.logic.dto.QuinielaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InscripcionService {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    public List<QuinielaResponse> obtenerQuinielasOrdenadasNoInscritas(int usuarioId) {
        List<Object[]> rows = inscripcionRepository.getQuinielasDisponibles(usuarioId);
        return rows.stream()
                .map(cols -> new QuinielaResponse(
                        (Integer) cols[0],
                        (String) cols[1],
                        (String) cols[2],
                        (String) cols[3],
                        ((Date) cols[4]).toLocalDate(),
                        ((Date) cols[5]).toLocalDate(),
                        (String) cols[6],
                        (String) cols[7],
                        (String) cols[8],
                        (Integer) cols[9]
                ))
                .collect(Collectors.toList());
    }
}
