package org.example.quinielasproyecto.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TorneoRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Object[]> obtenerTorneos() {
        return entityManager
                .createNativeQuery("EXEC sp_ObtenerNombresTorneos")
                .getResultList();
    }

}
