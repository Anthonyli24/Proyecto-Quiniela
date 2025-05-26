package org.example.quinielasproyecto.logic.dto;

public class LoginResponse {
    private boolean loginExitoso;
    private Integer usuarioId;
    private String nombre;
    private String correo;
    private String rol;
    private String estado;
    private String mensaje;


    public boolean isLoginExitoso() {
        return loginExitoso;
    }

    public void setLoginExitoso(boolean loginExitoso) {
        this.loginExitoso = loginExitoso;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
