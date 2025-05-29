package org.example.quinielasproyecto.presentation;

import org.example.quinielasproyecto.logic.dto.QuinielaRequest;
import org.example.quinielasproyecto.logic.dto.QuinielaResponse;
import org.example.quinielasproyecto.logic.service.QuinielaService;
import org.example.quinielasproyecto.logic.dto.QuinielaDetalleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/AdministrarQuinielas")
    public ResponseEntity<List<QuinielaResponse>> listarOrdenadas() {
        List<QuinielaResponse> lista = quinielaService.obtenerQuinielasOrdenadas();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}/QuinielaDetalles")
    public ResponseEntity<QuinielaDetalleResponse> detalles(@PathVariable int id) {
        QuinielaDetalleResponse detalle = quinielaService.obtenerQuinielaConPartidos(id);
        return ResponseEntity.ok(detalle);
    }

}