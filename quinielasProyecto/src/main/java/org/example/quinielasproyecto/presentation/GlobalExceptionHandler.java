package org.example.quinielasproyecto.presentation;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handleDataAccess(DataAccessException ex) {
        Throwable root = ex.getRootCause();
        if (root instanceof SQLServerException
                && ((SQLServerException) root).getErrorCode() == 50001) {
            // Solo imprimimos un log corto
            System.out.println(">> quiniela no encontrada: " + root.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(root.getMessage());
        }
        // cualquier otro error de BD
        System.err.println("ERROR BD: " + ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error interno en la base de datos");
    }
}
