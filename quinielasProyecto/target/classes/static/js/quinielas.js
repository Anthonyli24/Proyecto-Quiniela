// src/main/resources/static/js/quinielas.js

document.addEventListener('DOMContentLoaded', () => {
    const container = document.getElementById('quinielas-container');
    const apiUrl   = '/api/quinielas/AdministrarQuinielas';

    // 1) Cargar lista de quinielas
    fetch(apiUrl, { credentials: 'include' })
        .then(res => {
            if (!res.ok) throw new Error('Error al cargar quinielas');
            return res.json();
        })
        .then(quinielas => {
            container.innerHTML = ''; // limpiar
            if (!Array.isArray(quinielas) || quinielas.length === 0) {
                container.innerHTML = '<p>No hay quinielas registradas.</p>';
                return;
            }

            quinielas.forEach(q => {
                const card = document.createElement('div');
                card.className = 'card';
                card.innerHTML = `
          <h3 class="card-title">${q.nombre}</h3>
          <p class="card-text">
            Inscripciones:
            ${formatDate(q.fechaInicioInscripciones)}
            al
            ${formatDate(q.fechaFinalInscripciones)}
          </p>
          <p class="card-text">Estado: ${q.estado}</p>
          <button
            class="btn btn-secondary btn-ver"
            data-id="${q.quinielaId}">
            Ver Detalles
          </button>
        `;
                container.appendChild(card);
            });

            // 2) Ajustar el handler de “Ver Detalles” para ir al HTML
            container.querySelectorAll('.btn-ver').forEach(btn => {
                btn.addEventListener('click', e => {
                    const id = e.currentTarget.getAttribute('data-id');
                    // NAVEGA a la página estática que cargará el JSON internamente
                    window.location.href = `/admin/quiniela-detalle.html?id=${id}`;
                });
            });
        })
        .catch(err => {
            container.innerHTML = `<p class="error">${err.message}</p>`;
        });

    // 3) “Crear Quiniela” (sin función por ahora)
    document.getElementById('btnCrearQuiniela')
        .addEventListener('click', () => {
            window.location.href = '/crear_quiniela.html';
        });

    // Helper: formatea YYYY-MM-DD
    function formatDate(dstr) {
        const d = new Date(dstr);
        return d.toLocaleDateString();
    }
});
