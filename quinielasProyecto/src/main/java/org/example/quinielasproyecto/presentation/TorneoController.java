package org.example.quinielasproyecto.presentation;
import org.example.quinielasproyecto.logic.service.TorneoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/torneos")
public class TorneoController {

    @Autowired
    private TorneoService torneoService;

    @PostMapping("/registrar")
    public ResponseEntity<String> crearTorneo(
            @RequestParam String nombre,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) java.util.Date fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) java.util.Date fechaCierre,
            @RequestParam(required = false) String resultadoFinal) {

        try {
            torneoService.crearTorneo(nombre, new Date(fechaInicio.getTime()), new Date(fechaCierre.getTime()), resultadoFinal);
            return ResponseEntity.ok("Torneo creado correctamente");
        } catch (Exception ex) {
            Throwable causa = ex;
            while (causa.getCause() != null) {
                causa = causa.getCause();
            }

            String msg = causa.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg != null ? msg : "Error al registrar el torneo.");
        }
    }


    @GetMapping
    public List<Map<String, Object>> obtenerTorneos() {
        return torneoService.obtenerTorneos();
    }
}
