document.getElementById("quinielaForm").addEventListener("submit", function (e) {
    e.preventDefault();

    const data = {
        nombre: document.getElementById("nombre").value,
        descripcion: document.getElementById("descripcion").value,
        reglas: document.getElementById("reglas").value,
        fechaInicioInscripciones: document.getElementById("fechaInicioInscripciones").value,
        fechaFinalInscripciones: document.getElementById("fechaFinalInscripciones").value,
        estado: document.getElementById("estado").value,
        modalidad: document.getElementById("modalidad").value,
        tipoPuntuacion: document.getElementById("tipoPuntuacion").value,
        torneoId: parseInt(document.getElementById("torneoId").value)
    };

    fetch("/api/quinielas/registrar", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            if (!response.ok) {
                return response.text().then(msg => { throw new Error(msg); });
            }
            return response.text();
        })
        .then(message => {
            document.getElementById("mensaje").innerText = message;
            document.getElementById("mensaje").style.color = "green";
            document.getElementById("quinielaForm").reset();
            setTimeout(() => {
                window.location.href = "/admin/quinielas.html";
            }, 1000);
        })
        .catch(error => {
            document.getElementById("mensaje").innerText = "Error: " + error.message;
            document.getElementById("mensaje").style.color = "red";
        });
});

document.addEventListener('DOMContentLoaded', () => {
    const select = document.getElementById('torneoId');
    const apiUrl = '/api/quinielas/torneos';

    fetch(apiUrl, { credentials: 'include' })
        .then(res => {
            if (!res.ok) throw new Error("Error al cargar torneos");
            return res.json();
        })
        .then(torneos => {
            select.innerHTML = '<option value="">Seleccione un torneo</option>';
            torneos.forEach(t => {
                const opt = document.createElement('option');
                opt.value = t.id;
                opt.textContent = `Torneo ${t.id}` + (t.nombre ? ` - ${t.nombre}` : '');
                select.appendChild(opt);
            });
        })
        .catch(err => {
            console.error(err);
            select.innerHTML = '<option value="">Error al cargar</option>';
        });
});