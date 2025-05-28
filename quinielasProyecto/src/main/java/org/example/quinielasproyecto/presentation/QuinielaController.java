package org.example.quinielasproyecto.presentation;

import org.example.quinielasproyecto.logic.dto.QuinielaRequest;
import org.example.quinielasproyecto.logic.service.QuinielaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/quinielas")
public class QuinielaController {

    @Autowired
    private QuinielaService quinielaService;

    @PostMapping("/registrar")
    public ResponseEntity<?> crearQuiniela(@RequestBody QuinielaRequest request) {
        try {
            quinielaService.registrarQuiniela(request);
            return ResponseEntity.ok("Quiniela registrada exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al registrar quiniela: " + e.getMessage());
        }
    }
}