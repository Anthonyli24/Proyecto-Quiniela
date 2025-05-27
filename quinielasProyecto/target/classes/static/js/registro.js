document.getElementById('registroForm').addEventListener('submit', async function(e) {
    e.preventDefault();

    const contrasena = document.getElementById('contrasena').value;
    const confirmarContrasena = document.getElementById('confirmarContrasena').value;
    const mensajeDiv = document.getElementById('mensaje');

    // Validar que las contraseñas coincidan
    if (contrasena !== confirmarContrasena) {
        mensajeDiv.textContent = 'Las contraseñas no coinciden.';
        mensajeDiv.className = 'mensaje error';
        return;
    }

    const data = {
        nombre: document.getElementById('nombre').value,
        correo: document.getElementById('correo').value,
        nombreUsuario: document.getElementById('nombreUsuario').value,
        contraseña: contrasena,
        fechaNacimiento: document.getElementById('fechaNacimiento').value,
        estado: document.getElementById('estado').value,
        rolId: parseInt(document.getElementById('rolId').value),
    };

    try {
        const response = await fetch('/api/usuarios/registro', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        });

        const text = await response.text();

        if (response.ok) {
            mensajeDiv.textContent = text;
            mensajeDiv.className = 'mensaje exito';
            this.reset();
            setTimeout(() => {
                window.location.href = 'index.html';
            }, 1000);
        } else {
            mensajeDiv.textContent = text || 'Error en el registro.';
            mensajeDiv.className = 'mensaje error';
        }
    } catch (error) {
        mensajeDiv.textContent = 'Error de conexión: ' + error.message;
        mensajeDiv.className = 'mensaje error';
    }
});
