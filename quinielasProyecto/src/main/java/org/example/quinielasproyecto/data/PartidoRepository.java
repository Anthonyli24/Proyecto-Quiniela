package org.example.quinielasproyecto.data;

import jakarta.persistence.*;
import org.example.quinielasproyecto.logic.dto.PartidoRequest;
import org.example.quinielasproyecto.logic.dto.PartidoResponse;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PartidoRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public String insertarPartido(PartidoRequest request) {
        try {
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_InsertarPartido");

            query.registerStoredProcedureParameter("FechaPartido", java.sql.Date.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("HoraPartido", java.sql.Time.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("EquipoLocal", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("EquipoVisitante", Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("TorneoID", Integer.class, ParameterMode.IN);

            query.setParameter("FechaPartido", java.sql.Date.valueOf(request.getFechaPartido()));
            query.setParameter("HoraPartido", java.sql.Time.valueOf(request.getHoraPartido()));
            query.setParameter("EquipoLocal", request.getEquipoLocal());
            query.setParameter("EquipoVisitante", request.getEquipoVisitante());
            query.setParameter("TorneoID", request.getTorneoId());

            query.execute();

            return "Partido registrado exitosamente.";
        } catch (PersistenceException pe) {
            Throwable cause = pe.getCause();
            if (cause != null && cause.getMessage() != null) {
                return "Error del procedimiento: " + cause.getMessage();
            }
            return "Error al ejecutar el procedimiento almacenado.";
        } catch (Exception e) {
            return "Error inesperado al registrar partido: " + e.getMessage();
        }
    }

    public List<PartidoResponse> obtenerPartidos() {
        Query query = entityManager.createNativeQuery("EXEC sp_ObtenerPartidos");


        List<Object[]> resultados = query.getResultList();

        List<PartidoResponse> partidos = new ArrayList<>();

        for (Object[] fila : resultados) {
            PartidoResponse partido = new PartidoResponse(
                    ((Number) fila[0]).intValue(),      // partido_id
                    (String) fila[1],                   // equipo_local
                    (String) fila[2],                   // equipo_visitante
                    (String) fila[3],                   // estado_partido
                    ((Date) fila[4]).toLocalDate(),     // fecha_partido
                    ((Time) fila[5]).toLocalTime(),     // hora_partido
                    ((Number) fila[6]).intValue(),      // goles_local
                    ((Number) fila[7]).intValue(),      // goles_visitante
                    (String) fila[8]                    // torneo_nombre
            );
            partidos.add(partido);
        }
        return partidos;
    }
}
