// src/main/resources/static/js/quiniela-detalle.js

document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('btnBack').addEventListener('click', () => {
        window.location.href = '/admin/quinielas.html';
    });

    const params = new URLSearchParams(window.location.search);
    const id     = params.get('id');
    const detail = document.getElementById('quiniela-detail');
    const tbody  = document.querySelector('#partidos-table tbody');

    if (!id) {
        detail.innerHTML = '<p>ID de quiniela no especificado.</p>';
        return;
    }

    fetch(`/api/quinielas/${id}/QuinielaDetalles`, {
        credentials: 'include'
    })
        .then(res => {
            if (!res.ok) {
                throw new Error('No se pudieron cargar los detalles de la quiniela.');
            }
            return res.json();
        })
        .then(data => {
            const q = data.quiniela;
            detail.innerHTML = `
        <h1 class="card-title">${q.nombre}</h1>
        <p>${q.descripcion}</p>
        <p><strong>Reglas:</strong> ${q.reglas}</p>
        <p><strong>Inscripciones:</strong>
          ${formatDate(q.fechaInicioInscripciones)} 
          al 
          ${formatDate(q.fechaFinalInscripciones)}
        </p>
        <p><strong>Modalidad:</strong> ${q.modalidad}</p>
        <p><strong>Tipo de Puntuación:</strong> ${q.tipoPuntuacion}</p>
      `;

            tbody.innerHTML = '';
            if (Array.isArray(data.partidos) && data.partidos.length > 0) {
                data.partidos.forEach(p => {
                    const tr = document.createElement('tr');
                    tr.innerHTML = `
            <td>${p.partidoId}</td>
            <td>${p.equipoLocal || ''}</td>
            <td>${p.equipoVisitante || ''}</td>
            <td>${formatDate(p.fechaPartido)}</td>
            <td>${formatTime(p.horaPartido)}</td>
            <td>${p.golesLocal}</td>
            <td>${p.golesVisitante}</td>
          `;
                    tbody.appendChild(tr);
                });
            } else {
                tbody.innerHTML = `
          <tr>
            <td colspan="7">No hay partidos registrados para esta quiniela.</td>
          </tr>
        `;
            }
        })
        .catch(err => {
            detail.innerHTML = `<p class="error">${err.message}</p>`;
        });

    function formatDate(dstr) {
        const d = new Date(dstr);
        return d.toLocaleDateString();
    }

    function formatTime(tstr) {
        return tstr;
    }
});
