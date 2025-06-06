document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('btnBack').addEventListener('click', () => {
        window.location.href = '/jugador/jugar.html';
    });

    const params = new URLSearchParams(window.location.search);
    const id     = params.get('quinielaId');
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

                    let boton;
                    if (p.estadoPartido === 'Finalizado') {
                        boton = `<button class="btn btn-secondary btn-sm" disabled>Terminado</button>`;
                    } else {
                        boton = `<button class="btn btn-secondary btn-sm btn-pronosticar" data-id="${p.partidoId}">Pronosticar</button>`;
                    }

                    tr.innerHTML = `
                    <td>${p.equipoLocal || ''}</td>
                    <td>${p.equipoVisitante || ''}</td>
                    <td>${formatDate(p.fechaPartido)}</td>
                    <td>${formatTime(p.horaPartido)}</td>
                    <td>${p.golesLocal ?? ''}</td>
                    <td>${p.golesVisitante ?? ''}</td>
                    <td>${p.estadoPartido || 'Pendiente'}</td>
                    <td>${boton}</td>
                `;
                    tbody.appendChild(tr);
                });

                tbody.querySelectorAll('.btn-pronosticar').forEach(btn => {
                    btn.addEventListener('click', e => {
                        const partidoId = parseInt(e.currentTarget.getAttribute('data-id'));
                        const partido = data.partidos.find(p => p.partidoId === partidoId);
                        if (partido) abrirModal(partido);
                    });
                });

            } else {
                tbody.innerHTML = `
              <tr class="no-data-row">
                <td colspan="8">No hay partidos registrados para esta quiniela.</td>
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

    const modal = document.getElementById('pronostico-modal');
    const modalInfo = document.getElementById('modal-info');
    const inputGL = document.getElementById('input-gl');
    const inputGV = document.getElementById('input-gv');
    const btnConfirmar = document.getElementById('btn-confirmar');
    const btnCancelar = document.getElementById('btn-cancelar');

    let partidoSeleccionado = null;

    function abrirModal(partido) {
        partidoSeleccionado = partido;

        const ahora = new Date();
        modalInfo.innerHTML = `
            <strong>${partido.equipoLocal}</strong> vs 
            <strong>${partido.equipoVisitante}</strong><br>
            Fecha del partido: ${formatDate(partido.fechaPartido)}<br>
            Hora del partido: ${formatTime(partido.horaPartido)}
        `;

        inputGL.value = partido.golesLocal ?? '';
        inputGV.value = partido.golesVisitante ?? '';
        modal.style.display = 'flex';
    }

    function cerrarModal() {
        modal.style.display = 'none';
        partidoSeleccionado = null;
        inputGL.value = '';
        inputGV.value = '';
    }

    btnConfirmar.addEventListener('click', () => {
        const gl = parseInt(inputGL.value);
        const gv = parseInt(inputGV.value);

        if (isNaN(gl) || isNaN(gv)) {
            alert("Debes ingresar valores numéricos válidos para los goles.");
            return;
        }

        fetch('/api/pronosticar/registrar', {
            method: 'POST',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                partidoId: partidoSeleccionado.partidoId,
                golesLocal: gl,
                golesVisitante: gv,
                quinielaId: id,
                fechaPronostico: new Date().toISOString().split('T')[0],
                horaPronostico: new Date().toTimeString().split(' ')[0],
            })
        })
            .then(res => {
                if (!res.ok) throw new Error("Error al registrar el pronóstico.");
                return res.json();
            })
            .then(res => {
                // Actualiza dinámicamente la fila del partido
                const fila = document.querySelector(`button[data-id="${partidoSeleccionado.partidoId}"]`).closest('tr');

                if (fila) {
                    fila.children[4].textContent = gl; // Goles local
                    fila.children[5].textContent = gv; // Goles visitante

                    const btn = fila.querySelector('button.btn-pronosticar');
                    if (btn) {
                        btn.textContent = 'Pronosticado';
                        btn.disabled = true;
                        btn.classList.remove('btn-pronosticar');
                    }
                }
                cerrarModal();
            })
            .catch(err => {
                alert(err.message);
            });
    });

    btnCancelar.addEventListener('click', cerrarModal);
});
