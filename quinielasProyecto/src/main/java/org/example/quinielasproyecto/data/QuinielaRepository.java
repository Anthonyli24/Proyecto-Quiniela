package org.example.quinielasproyecto.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.example.quinielasproyecto.logic.dto.QuinielaRequest;
import org.springframework.stereotype.Repository;

@Repository
public class QuinielaRepository {

    @PersistenceContext
    private EntityManager entityManager;


    public void registrarQuiniela(QuinielaRequest request) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("sp_RegistrarQuiniela");

        query.registerStoredProcedureParameter("QuinielaID", Integer.class, jakarta.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("Nombre", String.class, jakarta.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("Descripcion", String.class, jakarta.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("Reglas", String.class, jakarta.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("FechaInicioInscripciones", java.sql.Date.class, jakarta.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("FechaFinalInscripciones", java.sql.Date.class, jakarta.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("Estado", String.class, jakarta.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("Modalidad", String.class, jakarta.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("TipoPuntuacion", String.class, jakarta.persistence.ParameterMode.IN);
        query.registerStoredProcedureParameter("TorneoID", Integer.class, jakarta.persistence.ParameterMode.IN);

        query.setParameter("QuinielaID", request.getQuinielaId());
        query.setParameter("Nombre", request.getNombre());
        query.setParameter("Descripcion", request.getDescripcion());
        query.setParameter("Reglas", request.getReglas());
        query.setParameter("FechaInicioInscripciones", java.sql.Date.valueOf(request.getFechaInicioInscripciones()));
        query.setParameter("FechaFinalInscripciones", java.sql.Date.valueOf(request.getFechaFinalInscripciones()));
        query.setParameter("Estado", request.getEstado());
        query.setParameter("Modalidad", request.getModalidad());
        query.setParameter("TipoPuntuacion", request.getTipoPuntuacion());
        query.setParameter("TorneoID", request.getTorneoId());

        query.execute();
    }
}
