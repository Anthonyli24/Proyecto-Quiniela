const loginForm = document.getElementById("loginForm");
const errorMsg = document.getElementById("errorMsg");

loginForm.addEventListener("submit", async function (e) {
    e.preventDefault();

    const usuario = document.getElementById("usuario").value;
    const contrasena = document.getElementById("contrasena").value;

    try {
        const response = await fetch("/api/usuarios/login", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                nombreUsuario: usuario,
                contraseña: contrasena
            })
        });

        if (!response.ok) throw new Error("Error al iniciar sesión");

        const data = await response.json();

        if (data.loginExitoso) {
            window.location.href = "/dashboard.html"; // Cambia esto por tu vista principal
        } else {
            errorMsg.style.display = "block";
            errorMsg.textContent = data.mensaje || "Credenciales inválidas";
        }
    } catch (error) {
        console.error("Error:", error);
        errorMsg.style.display = "block";
        errorMsg.textContent = "Error de conexión";
    }
});
