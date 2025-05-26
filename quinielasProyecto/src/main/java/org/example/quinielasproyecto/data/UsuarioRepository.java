package org.example.quinielasproyecto.data;


import jakarta.persistence.*;
import org.example.quinielasproyecto.logic.dto.LoginResponse;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UsuarioRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public LoginResponse validarLogin(String nombreUsuario, String contraseña) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("sp_ValidarLogin");

        query.registerStoredProcedureParameter("nombre_usuario", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("contraseña", String.class, ParameterMode.IN);

        query.setParameter("nombre_usuario", nombreUsuario);
        query.setParameter("contraseña", contraseña);

        // Ejecutar el procedimiento
        boolean hasResultSet = query.execute();

        if (hasResultSet) {
            List<Object[]> resultList = query.getResultList();

            if (!resultList.isEmpty()) {
                Object[] row = resultList.get(0);
                LoginResponse response = new LoginResponse();

                response.setLoginExitoso(row[0] != null && ((Number) row[0]).intValue() == 1);
                response.setUsuarioId(row[1] != null ? ((Number) row[1]).intValue() : null);
                response.setNombre(row[2] != null ? row[2].toString() : null);
                response.setCorreo(row[3] != null ? row[3].toString() : null);
                response.setRol(row[4] != null ? row[4].toString() : null);
                response.setEstado(row[5] != null ? row[5].toString() : null);
                response.setMensaje(row[6] != null ? row[6].toString() : "No message");

                return response;
            }
        }

        // Si no hay resultados
        LoginResponse response = new LoginResponse();
        response.setLoginExitoso(false);
        response.setMensaje("Usuario o contraseña incorrectos");
        return response;
    }
}
