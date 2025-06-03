package org.example.quinielasproyecto.data;

import org.example.quinielasproyecto.logic.Entidades.Torneo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TorneoJPARepository extends JpaRepository<Torneo, Integer> {
    @Query(value = "EXEC sp_CrearTorneo :nombre, :fechaInicio, :fechaCierre, :resultadoFinal", nativeQuery = true)
    void crearTorneo(
            @Param("nombre") String nombre,
            @Param("fechaInicio") java.sql.Date fechaInicio,
            @Param("fechaCierre") java.sql.Date fechaCierre,
            @Param("resultadoFinal") String resultadoFinal
    );
}