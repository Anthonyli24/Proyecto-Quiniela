package org.example.quinielasproyecto.presentation;

import jakarta.servlet.http.HttpSession;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.example.quinielasproyecto.data.InscripcionRepository;
import org.example.quinielasproyecto.data.QuinielaJpaRepository;
import org.example.quinielasproyecto.data.UsuarioJpaRepository;
import org.example.quinielasproyecto.logic.Inscripcion;
import org.example.quinielasproyecto.logic.Quiniela;
import org.example.quinielasproyecto.logic.Usuario;
import org.example.quinielasproyecto.logic.dto.QuinielaDTO;
import org.example.quinielasproyecto.logic.dto.QuinielaResponse;
import org.example.quinielasproyecto.logic.dto.RankingDTO;
import org.example.quinielasproyecto.logic.service.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionController {

    @Autowired
    private InscripcionRepository inscripcionRepo;

    @Autowired
    private UsuarioJpaRepository usuarioRepo;

    @Autowired
    private QuinielaJpaRepository quinielaRepo;

    @Autowired
    private InscripcionService inscripcionService;
    @Autowired
    private UsuarioJpaRepository usuarioJpaRepository;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarInscripcion(@RequestBody Map<String, Integer> payload, HttpSession session) {
        Object usuarioIdObj = session.getAttribute("usuarioId");

        if (usuarioIdObj == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No ha iniciado sesión");
        }

        Integer usuarioId = (Integer) usuarioIdObj;
        Integer quinielaId = payload.get("quinielaId");

        Optional<Usuario> usuario = usuarioRepo.findById(usuarioId);
        Optional<Quiniela> quiniela = quinielaRepo.findById(quinielaId);

        if (usuario.isEmpty() || quiniela.isEmpty()) {
            return ResponseEntity.badRequest().body("Usuario o quiniela no encontrados");
        }

        boolean yaInscrito = inscripcionRepo.existsByUsuarioAndQuiniela(usuario.get(), quiniela.get());
        if (yaInscrito) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya está inscrito en esta quiniela");
        }

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setId(null);
        inscripcion.setUsuario(usuario.get());
        inscripcion.setQuiniela(quiniela.get());
        inscripcion.setPuntaje(0);

        inscripcionRepo.save(inscripcion);

        return ResponseEntity.ok("Inscripción registrada exitosamente");
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<QuinielaResponse>> obtenerQuinielasDisponibles(HttpSession session) {
        Object usuarioIdObj = session.getAttribute("usuarioId");

        if (usuarioIdObj == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        int usuarioId = (Integer) usuarioIdObj;

        List<QuinielaResponse> quinielas = inscripcionService.obtenerQuinielasOrdenadasNoInscritas(usuarioId);
        return ResponseEntity.ok(quinielas);
    }

    @GetMapping("/usuarios/{usuarioId}/quinielas")
    public List<QuinielaDTO> getQuinielasDeUsuario(@PathVariable Long usuarioId) {
        return inscripcionService.obtenerQuinielasDeUsuario(usuarioId);
    }

    @GetMapping("/quinielas/{quinielaId}/ranking")
    public List<RankingDTO> getRankingDeQuiniela(@PathVariable Long quinielaId) {
        return inscripcionService.obtenerRankingPorQuiniela(quinielaId);
    }
}