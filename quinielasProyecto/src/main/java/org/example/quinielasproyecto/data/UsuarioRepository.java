package org.example.quinielasproyecto.data;


import jakarta.persistence.*;
import org.example.quinielasproyecto.logic.dto.LoginResponse;
import org.example.quinielasproyecto.logic.dto.RegistroRequest;
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






    public String registrarUsuario(RegistroRequest request) {
        try {
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("RegistrarUsuario");

            query.registerStoredProcedureParameter("nombre", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("correo", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("nombre_usuario", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("contraseña", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("fecha_nacimiento", java.sql.Date.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("estado", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("rol_id", Integer.class, ParameterMode.IN);

            query.setParameter("nombre", request.getNombre());
            query.setParameter("correo", request.getCorreo());
            query.setParameter("nombre_usuario", request.getNombreUsuario());
            query.setParameter("contraseña", request.getContraseña());
            query.setParameter("fecha_nacimiento", java.sql.Date.valueOf(request.getFechaNacimiento()));
            query.setParameter("estado", request.getEstado());
            query.setParameter("rol_id", request.getRolId());

            query.execute();
            return "Usuario registrado exitosamente.";
        } catch (Exception e) {
            return "Error en el registro: " + e.getMessage();
        }
    }






}
