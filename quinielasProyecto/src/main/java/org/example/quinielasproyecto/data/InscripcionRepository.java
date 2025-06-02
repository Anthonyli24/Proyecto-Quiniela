package org.example.quinielasproyecto.data;

import org.example.quinielasproyecto.logic.Inscripcion;
import org.example.quinielasproyecto.logic.Quiniela;
import org.example.quinielasproyecto.logic.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Integer> {
    boolean existsByUsuarioAndQuiniela(Usuario usuario, Quiniela quiniela);

    @Query(value = "EXEC usp_get_quinielas_disponibles :usuarioId", nativeQuery = true)
    List<Object[]> getQuinielasDisponibles(@Param("usuarioId") int usuarioId);

    List<Inscripcion> findByUsuarioId(Long usuarioId);

    List<Inscripcion> findByQuinielaIdOrderByPuntajeDesc(Long quinielaId);
}