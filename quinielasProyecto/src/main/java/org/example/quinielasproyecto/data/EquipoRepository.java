package org.example.quinielasproyecto.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EquipoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Map<String, Object>> obtenerEquipos() {
        List<Object[]> filas = entityManager
                .createNativeQuery("EXEC sp_ObtenerEquipos")
                .getResultList();

        List<Map<String, Object>> resultado = new ArrayList<>();

        for (Object[] fila : filas) {
            Map<String, Object> equipo = new HashMap<>();
            equipo.put("id", ((Number) fila[0]).intValue());
            equipo.put("nombre", fila[1].toString());
            resultado.add(equipo);
        }

        return resultado;
    }
}

