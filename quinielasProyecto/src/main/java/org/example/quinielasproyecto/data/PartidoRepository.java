package org.example.quinielasproyecto.data;

import jakarta.persistence.*;
import org.example.quinielasproyecto.logic.dto.PartidoRequest;
import org.springframework.stereotype.Repository;

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
}
