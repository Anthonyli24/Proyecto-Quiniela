package org.example.quinielasproyecto.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import org.example.quinielasproyecto.logic.dto.QuinielaRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@Repository
public class QuinielaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void registrarQuiniela(QuinielaRequest request) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("sp_RegistrarQuiniela");


        query.registerStoredProcedureParameter("Nombre", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Descripcion", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Reglas", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("FechaInicioInscripciones", java.sql.Date.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("FechaFinalInscripciones", java.sql.Date.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Estado", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Modalidad", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("TipoPuntuacion", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("TorneoID", Integer.class, ParameterMode.IN);


        query.setParameter("Nombre", request.getNombre());
        query.setParameter("Descripcion", request.getDescripcion());
        query.setParameter("Reglas", request.getReglas());
        query.setParameter("FechaInicioInscripciones",
                java.sql.Date.valueOf(request.getFechaInicioInscripciones()));
        query.setParameter("FechaFinalInscripciones",
                java.sql.Date.valueOf(request.getFechaFinalInscripciones()));
        query.setParameter("Estado", request.getEstado());
        query.setParameter("Modalidad", request.getModalidad());
        query.setParameter("TipoPuntuacion", request.getTipoPuntuacion());
        query.setParameter("TorneoID", request.getTorneoId());

        query.execute();
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> obtenerQuinielasOrdenadas() {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("usp_get_quinielas_ordenadas");

        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public Map<String, List<Object[]>> obtenerQuinielaConPartidos(int quinielaId) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("usp_get_quiniela_con_partidos");

        query.registerStoredProcedureParameter("quiniela_id", Integer.class, ParameterMode.IN);
        query.setParameter("quiniela_id", quinielaId);

        boolean hasResult = query.execute();

        List<Object[]> datosQuiniela = new ArrayList<>();
        List<Object[]> datosPartidos = new ArrayList<>();

        if (hasResult) {
            datosQuiniela = query.getResultList();
        }
        if (query.hasMoreResults()) {
            datosPartidos = query.getResultList();
        }

        Map<String, List<Object[]>> resultado = new HashMap<>();
        resultado.put("quiniela", datosQuiniela);
        resultado.put("partidos", datosPartidos);

        return resultado;
    }
}
