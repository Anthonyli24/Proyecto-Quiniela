package org.example.quinielasproyecto.presentation;


import jakarta.servlet.http.HttpSession;
import org.example.quinielasproyecto.logic.dto.LoginRequest;
import org.example.quinielasproyecto.logic.dto.LoginResponse;
import org.example.quinielasproyecto.logic.dto.RegistroRequest;
import org.example.quinielasproyecto.logic.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public String redirigirLogin() {
        return "redirect:/index.html";
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        LoginResponse response = usuarioService.login(loginRequest.getNombreUsuario(), loginRequest.getContraseña());

        if (response.isLoginExitoso()) {
            session.setAttribute("usuarioId", response.getUsuarioId());
            session.setAttribute("nombreUsuario", loginRequest.getNombreUsuario());
            session.setAttribute("rol", response.getRol());

            if (response.getRol().equalsIgnoreCase("Administrador")) {
                response.setMensaje("Login exitoso - redirigir a /admin-dashboard");
            } else if (response.getRol().equalsIgnoreCase("Jugador")) {
                response.setMensaje("Login exitoso - redirigir a /jugador-dashboard");
            }
        }

        System.out.println(response.toString());
        return response;
    }



    @GetMapping("/perfil")
    public LoginResponse obtenerPerfil(HttpSession session) {
        Object usuarioId = session.getAttribute("usuarioId");
        Object rol = session.getAttribute("rol");

        if (usuarioId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No ha iniciado sesión");
        }

        LoginResponse response = new LoginResponse();
        response.setLoginExitoso(true);
        response.setUsuarioId((Integer) usuarioId);
        response.setRol((String) rol);
        response.setMensaje("Perfil recuperado con éxito");

        return response;
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "Sesión cerrada";
    }


    @PostMapping("/registro")
    public String registrar(@RequestBody RegistroRequest request) {
        return usuarioService.registrarUsuario(request);
    }


}
