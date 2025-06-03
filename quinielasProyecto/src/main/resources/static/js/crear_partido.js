document.addEventListener("DOMContentLoaded", () => {
    const torneoSelect = document.getElementById("torneoId");
    const equipoLocalSelect = document.getElementById("equipoLocal");
    const equipoVisitanteSelect = document.getElementById("equipoVisitante");
    const mensajeDiv = document.getElementById("mensaje");


    fetch("/api/torneos", { credentials: "include" })
        .then(res => res.json())
        .then(data => {
            torneoSelect.innerHTML = '<option value="">Seleccione un torneo</option>';
            data.forEach(torneo => {
                const opt = document.createElement("option");
                opt.value = torneo.id || torneo.torneoId || torneo.idTorneo;
                opt.textContent = torneo.nombre || torneo.torneo_nombre;
                torneoSelect.appendChild(opt);
            });
        })
        .catch(() => {
            torneoSelect.innerHTML = '<option value="">No se pudieron cargar los torneos</option>';
        });


    let equipos = [];
    fetch("/api/equipos", { credentials: "include" })
        .then(res => res.json())
        .then(data => {
            equipos = data;
            renderEquipos();
        })
        .catch(() => {
            equipoLocalSelect.innerHTML = '<option value="">No se pudieron cargar los equipos</option>';
            equipoVisitanteSelect.innerHTML = '<option value="">No se pudieron cargar los equipos</option>';
        });

    function renderEquipos() {
        const localValue = equipoLocalSelect.value;
        const visitanteValue = equipoVisitanteSelect.value;

        equipoLocalSelect.innerHTML = '<option value="">Seleccione equipo local</option>';
        equipoVisitanteSelect.innerHTML = '<option value="">Seleccione equipo visitante</option>';

        equipos.forEach(equipo => {
            const id = equipo.id || equipo.equipoId || equipo.idEquipo;
            const nombre = equipo.nombre || equipo.equipo_nombre;

            // Equipo Local
            const optLocal = document.createElement("option");
            optLocal.value = id;
            optLocal.textContent = nombre;
            if (id === visitanteValue && visitanteValue !== "") {
                optLocal.disabled = true;
            }
            equipoLocalSelect.appendChild(optLocal);

            // Equipo Visitante
            const optVisitante = document.createElement("option");
            optVisitante.value = id;
            optVisitante.textContent = nombre;
            if (id === localValue && localValue !== "") {
                optVisitante.disabled = true;
            }
            equipoVisitanteSelect.appendChild(optVisitante);
        });

        equipoLocalSelect.value = localValue;
        equipoVisitanteSelect.value = visitanteValue;
    }

    equipoLocalSelect.addEventListener("change", renderEquipos);
    equipoVisitanteSelect.addEventListener("change", renderEquipos);

    document.getElementById("partidoForm").addEventListener("submit", function(e) {
        e.preventDefault();
        mensajeDiv.textContent = "";

        const body = {
            equipoLocal: equipoLocalSelect.value,
            equipoVisitante: equipoVisitanteSelect.value,
            estadoPartido: document.getElementById("estado").value,
            fechaPartido: document.getElementById("fechaPartido").value,
            horaPartido: document.getElementById("horaPartido").value,
            golesLocal: parseInt(document.getElementById("golesLocal")?.value || "0", 10),
            golesVisitante: parseInt(document.getElementById("golesVisitante")?.value || "0", 10),
            torneoId: torneoSelect.value
        };

        fetch("/api/partidos/registrar", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            credentials: "include",
            body: JSON.stringify(body)
        })
        .then(res => res.text().then(text => ({ ok: res.ok, text })))
        .then(({ ok, text }) => {
            mensajeDiv.textContent = text;
            mensajeDiv.style.color = ok ? "green" : "red";
            if (ok) {
                this.reset();
                setTimeout(() => {
                    window.location.href = "partidos.html";
                }, 1000);
            }
        })
        .catch(() => {
            mensajeDiv.textContent = "Error al registrar el partido.";
            mensajeDiv.style.color = "red";
        });
    });
});
