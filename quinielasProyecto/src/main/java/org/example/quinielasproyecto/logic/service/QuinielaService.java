package org.example.quinielasproyecto.logic.service;
import org.example.quinielasproyecto.data.QuinielaRepository;
import org.example.quinielasproyecto.logic.dto.QuinielaRequest;
import org.example.quinielasproyecto.logic.dto.QuinielaResponse;
import org.example.quinielasproyecto.logic.dto.PartidoResponse;
import org.example.quinielasproyecto.logic.dto.QuinielaDetalleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataAccessException;
import jakarta.persistence.PersistenceException;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.example.quinielasproyecto.logic.exception.ResourceNotFoundException;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class QuinielaService {

    @Autowired
    private QuinielaRepository quinielaRepository;

    public void registrarQuiniela(QuinielaRequest request) {
        quinielaRepository.registrarQuiniela(request);
    }

    public List<QuinielaResponse> obtenerQuinielasOrdenadas() {
        List<Object[]> rows = quinielaRepository.obtenerQuinielasOrdenadas();
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

    public QuinielaDetalleResponse obtenerQuinielaConPartidos(int id) {
        try {
            Map<String, List<Object[]>> result =
                    quinielaRepository.obtenerQuinielaConPartidos(id);

            List<Object[]> quinielaRows = result.get("quiniela");
            if (quinielaRows == null || quinielaRows.isEmpty()) {
                throw new RuntimeException("Quiniela con id " + id + " no encontrada.");
            }

            Object[] q = quinielaRows.get(0);
            QuinielaResponse quiniela = new QuinielaResponse(
                    (Integer) q[0],
                    (String)  q[1],
                    (String)  q[2],
                    (String)  q[3],
                    ((java.sql.Date)   q[4]).toLocalDate(),
                    ((java.sql.Date)   q[5]).toLocalDate(),
                    (String)  q[6],
                    (String)  q[7],
                    (String)  q[8],
                    (Integer) q[9]
            );

            List<PartidoResponse> partidos = result.get("partidos").stream()
                    .map(cols -> new PartidoResponse(
                            (Integer) cols[0],
                            (String)  cols[1],
                            (String)  cols[2],
                            (String)  cols[3],
                            ((java.sql.Date) cols[4]).toLocalDate(),
                            ((java.sql.Time) cols[5]).toLocalTime(),
                            (Integer) cols[6],
                            (Integer) cols[7]
                    ))
                    .collect(Collectors.toList());

            return new QuinielaDetalleResponse(quiniela, partidos);

        } catch (DataAccessException ex) {
            Throwable root = ex.getRootCause();
            if (root instanceof SQLServerException
                    && ((SQLServerException) root).getErrorCode() == 50001) {
                System.out.println(">> quiniela no encontrada: " + root.getMessage());
                throw new ResourceNotFoundException(root.getMessage());
            }
            throw ex;
        }
    }
}
