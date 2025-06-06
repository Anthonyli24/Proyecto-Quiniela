document.addEventListener("DOMContentLoaded", () => {
    const tbody = document.getElementById("usuarios-table-body");

    fetch("/api/usuarios")
        .then(res => res.json())
        .then(usuarios => {
            if (usuarios.length === 0) {
                const tr = document.createElement("tr");
                tr.innerHTML = `<td colspan="7">No hay usuarios registrados.</td>`;
                tbody.appendChild(tr);
                return;
            }

            usuarios.forEach(usuario => {
                const tr = document.createElement("tr");
                tr.innerHTML = `
                    <td>${usuario.usuario_id}</td>
                    <td>${usuario.nombre}</td>
                    <td>${usuario.correo}</td>
                    <td>${usuario.nombre_usuario}</td>
                    <td>${usuario.fecha_nacimiento}</td>
                    <td><button class="btn-estado" data-id="${usuario.usuario_id}">${usuario.estado}</button></td>
                    <td>${usuario.rol}</td>
                `;
                tbody.appendChild(tr);
            });

            document.querySelectorAll(".btn-estado").forEach(button => {
                button.addEventListener("click", () => {
                    const id = button.getAttribute("data-id");

                    fetch(`/api/usuarios/${id}/estado`, {
                        method: "PUT"
                    })
                        .then(res => res.text())
                        .then(nuevoEstado => {
                            button.textContent = nuevoEstado;
                        })
                        .catch(() => {
                            alert("No se pudo cambiar el estado del usuario.");
                        });
                });
            });
        })
        .catch(() => {
            const tr = document.createElement("tr");
            tr.innerHTML = `<td colspan="7" style="color:red;">Error al cargar los usuarios.</td>`;
            tbody.appendChild(tr);
        });
});
