package org.example.quinielasproyecto.logic.service;

import org.example.quinielasproyecto.data.UsuarioJpaRepository;
import org.example.quinielasproyecto.data.UsuarioRepository;
import org.example.quinielasproyecto.logic.Entidades.Usuario;
import org.example.quinielasproyecto.logic.dto.LoginResponse;
import org.example.quinielasproyecto.logic.dto.RegistroRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioJpaRepository usuarioJpaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public LoginResponse login(String nombreUsuario, String contraseña) {
        return usuarioRepository.validarLogin(nombreUsuario, contraseña);
    }


    public String registrarUsuario(RegistroRequest request) {
        return usuarioRepository.registrarUsuario(request);
    }

    public List<Map<String, Object>> obtenerUsuariosNoAdmin() {
        List<Object[]> resultados = usuarioJpaRepository.obtenerUsuariosNoAdministradores();

        List<Map<String, Object>> usuarios = new ArrayList<>();

        for (Object[] fila : resultados) {
            Map<String, Object> usuario = new HashMap<>();
            usuario.put("usuario_id", fila[0]);
            usuario.put("nombre", fila[1]);
            usuario.put("correo", fila[2]);
            usuario.put("nombre_usuario", fila[3]);
            usuario.put("fecha_nacimiento", fila[4]);
            usuario.put("estado", fila[5]);
            usuario.put("rol_id", fila[6]);
            usuario.put("rol", fila[7]);

            usuarios.add(usuario);
        }

        return usuarios;
    }

    public String cambiarEstadoUsuario(int id) {
        return usuarioJpaRepository.cambiarEstadoUsuario(id);
    }

}