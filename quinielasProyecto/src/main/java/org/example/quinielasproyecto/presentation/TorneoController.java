package org.example.quinielasproyecto.presentation;
import org.example.quinielasproyecto.logic.service.TorneoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
    public String crearTorneo(
            @RequestParam String nombre,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) java.util.Date fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) java.util.Date fechaCierre,
            @RequestParam(required = false) String resultadoFinal) {
        try {
            torneoService.crearTorneo(nombre, new Date(fechaInicio.getTime()), new Date(fechaCierre.getTime()), resultadoFinal);
            return "Torneo creado correctamente";
        } catch (Exception ex) {
            String msg = ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage();
            return msg != null ? msg : "Error al registrar el torneo.";
        }
    }

    @GetMapping
    public List<Map<String, Object>> obtenerTorneos() {
        return torneoService.obtenerTorneos();
    }
}
