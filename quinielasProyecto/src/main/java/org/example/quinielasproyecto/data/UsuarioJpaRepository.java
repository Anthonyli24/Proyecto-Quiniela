package org.example.quinielasproyecto.data;

import org.example.quinielasproyecto.logic.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioJpaRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);

    @Query(value = "EXEC sp_ObtenerUsuariosNoAdministradores", nativeQuery = true)
    List<Object[]> obtenerUsuariosNoAdministradores();

    @Query(value = "EXEC sp_CambiarEstadoUsuario :usuarioId", nativeQuery = true)
    String cambiarEstadoUsuario(@Param("usuarioId") int usuarioId);

}

