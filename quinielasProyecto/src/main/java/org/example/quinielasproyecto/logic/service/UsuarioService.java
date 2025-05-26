package org.example.quinielasproyecto.logic.service;

import org.example.quinielasproyecto.data.UsuarioRepository;
import org.example.quinielasproyecto.logic.dto.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public LoginResponse login(String nombreUsuario, String contraseña) {
        return usuarioRepository.validarLogin(nombreUsuario, contraseña);
    }
}