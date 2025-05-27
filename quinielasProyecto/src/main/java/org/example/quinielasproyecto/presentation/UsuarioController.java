package org.example.quinielasproyecto.presentation;


import org.example.quinielasproyecto.logic.dto.LoginRequest;
import org.example.quinielasproyecto.logic.dto.LoginResponse;
import org.example.quinielasproyecto.logic.dto.RegistroRequest;
import org.example.quinielasproyecto.logic.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return usuarioService.login(loginRequest.getNombreUsuario(), loginRequest.getContraseña());
    }



    @PostMapping("/registro")
    public String registrar(@RequestBody RegistroRequest request) {
        return usuarioService.registrarUsuario(request);
    }


}
