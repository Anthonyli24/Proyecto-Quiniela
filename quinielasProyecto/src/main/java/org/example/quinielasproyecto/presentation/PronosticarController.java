package org.example.quinielasproyecto.presentation;

import jakarta.servlet.http.HttpSession;
import org.example.quinielasproyecto.data.PartidoJpaRepository;
import org.example.quinielasproyecto.data.QuinielaJpaRepository;
import org.example.quinielasproyecto.data.UsuarioJpaRepository;
import org.example.quinielasproyecto.logic.Entidades.Partido;
import org.example.quinielasproyecto.logic.Entidades.Pronostico;
import org.example.quinielasproyecto.logic.Entidades.Quiniela;
import org.example.quinielasproyecto.logic.Entidades.Usuario;
import org.example.quinielasproyecto.logic.dto.PronosticoResponseDTO;
import org.example.quinielasproyecto.logic.service.PronosticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/pronosticar")
public class PronosticarController {

    @Autowired
    private PronosticoService pronosticoService; // Asegúrate de tener un servicio para manejar la lógica de negocio

    @Autowired
    private UsuarioJpaRepository usuarioJpaRepository;

    @Autowired
    private PartidoJpaRepository partidoJpaRepository;

    @Autowired
    private QuinielaJpaRepository quinielaJpaRepository;




    @PostMapping("/registrar")
    public ResponseEntity<?> registrarPronostico(@RequestBody PronosticoResponseDTO dto, HttpSession session) {
        try {
            Object obj = session.getAttribute("usuarioId");
            if (obj == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("error", "Usuario no autenticado"));
            }

            Long usuarioId = (obj instanceof Integer) ? ((Integer) obj).longValue() : (Long) obj;

            if (usuarioId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("error", "Usuario no autenticado"));
            }

            // Validación básica (ejemplo)
            if (dto.partidoId() == null || dto.quinielaId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error", "Datos incompletos del pronóstico"));
            }

            Pronostico pron = new Pronostico();
            Optional<Usuario> user = usuarioJpaRepository.findById(Math.toIntExact(usuarioId));
            Optional<Partido> partido = partidoJpaRepository.findById(dto.partidoId());
            Optional<Quiniela> quin = quinielaJpaRepository.findById(Math.toIntExact(dto.quinielaId()));



            pron.setUsuario(user.get());
            pron.setPartido(partido.get());
            pron.setGolesLocal(dto.golesLocal());
            pron.setGolesVisita(dto.golesVisitante());
            pron.setQuiniela(quin.get());
            pron.setFechaPronostico(LocalDate.parse(dto.fechaPronostico()));
            pron.setHoraPronostico(LocalTime.parse(dto.horaPronostico()));
            pronosticoService.registrarPronostico(pron);





            return ResponseEntity.ok(Map.of("mensaje", "Pronóstico registrado correctamente"));

        } catch (Exception e) {
            e.printStackTrace(); // Para depuración
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al registrar el pronóstico"));
        }
    }




}
