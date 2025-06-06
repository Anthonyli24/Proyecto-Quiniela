package org.example.quinielasproyecto.presentation;

import org.example.quinielasproyecto.data.PartidoJpaRepository;
import org.example.quinielasproyecto.logic.Entidades.Partido;
import org.example.quinielasproyecto.logic.dto.PartidoRequest;
import org.example.quinielasproyecto.logic.dto.PartidoResponse;
import org.example.quinielasproyecto.logic.dto.finalizarDTO;
import org.example.quinielasproyecto.logic.service.PartidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/partidos")
public class PartidoController {

    @Autowired
    private PartidoService partidoService;
    @Autowired
    private PartidoJpaRepository partidoJpaRepository;

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


    @PostMapping("/finalizar")
    public ResponseEntity<?> finalizarPartido(@RequestBody finalizarDTO finalizarDTO) {
        Partido partido = partidoJpaRepository.findById(Long.parseLong(finalizarDTO.id()))
                .orElseThrow(() -> new RuntimeException("Partido no encontrado"));

        // Generar goles aleatorios entre 0 y 6
        int golesLocal = (int) (Math.random() * 7);
        int golesVisitante = (int) (Math.random() * 7);

        // Asignar los goles al partido
        partido.setGolesLocal(golesLocal);
        partido.setGolesVisitante(golesVisitante);

        // (Opcional) Cambiar estado del partido a "Finalizado"
        partido.setEstadoPartido("Finalizado");

        // Guardar los cambios
        partidoJpaRepository.save(partido);

        return ResponseEntity.ok(Map.of(
                "message", "Partido finalizado con éxito",
                "golesLocal", golesLocal,
                "golesVisitante", golesVisitante
        ));
    }
}
