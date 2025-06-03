package org.example.quinielasproyecto.presentation;

import org.example.quinielasproyecto.logic.dto.PartidoRequest;
import org.example.quinielasproyecto.logic.dto.PartidoResponse;
import org.example.quinielasproyecto.logic.service.PartidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partidos")
public class PartidoController {

    @Autowired
    private PartidoService partidoService;

    @PostMapping("/registrar")
    public ResponseEntity<String> registrarPartido(@RequestBody PartidoRequest request) {
        String resultado = partidoService.registrarPartido(request);
        if (resultado.contains("exitosamente")) {
            return ResponseEntity.ok(resultado);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultado);
        }
    }

    @GetMapping
    public List<PartidoResponse> obtenerPartidos() {
        return partidoService.obtenerPartidos();
    }
}
