package org.example.quinielasproyecto.presentation;

import org.example.quinielasproyecto.data.TorneoRepository;
import org.example.quinielasproyecto.logic.Entidades.Torneo;
import org.example.quinielasproyecto.logic.dto.QuinielaRequest;
import org.example.quinielasproyecto.logic.dto.QuinielaResponse;
import org.example.quinielasproyecto.logic.service.QuinielaService;
import org.example.quinielasproyecto.logic.dto.QuinielaDetalleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/quinielas")
public class QuinielaController {

    @Autowired
    private QuinielaService quinielaService;

    @Autowired
    private TorneoRepository torneoRepository;

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

    @GetMapping("/torneos")
    public ResponseEntity<List<Map<String, Object>>> obtenerTorneos() {
        List<Torneo> torneos = torneoRepository.findAll();

        List<Map<String, Object>> result = torneos.stream().map(torneo -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", torneo.getId());
            map.put("nombre", torneo.getTorneoNombre());
            return map;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }

}