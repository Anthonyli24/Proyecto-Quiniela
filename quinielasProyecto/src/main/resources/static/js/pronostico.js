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
                    <td>${p.golesLocal}</td>
                    <td>${p.golesVisitante}</td>
                    <td>${p.estadoPartido || 'Pendiente'}</td>
                    <td>${boton}</td>
                `;
                    tbody.appendChild(tr);
                });

                tbody.querySelectorAll('.btn-pronosticar').forEach(btn => {
                    btn.addEventListener('click', e => {
                        console.log("Click detectado en botón pronosticar");
                        const partidoId = parseInt(e.currentTarget.getAttribute('data-id'));
                        const partido = data.partidos.find(p => p.partidoId === partidoId);
                        console.log("Partido encontrado:", partido);
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

    // Referencias al modal y sus campos
    const modal = document.getElementById('pronostico-modal');
    const modalInfo = document.getElementById('modal-info');
    const inputGL = document.getElementById('input-gl');
    const inputGV = document.getElementById('input-gv');
    const btnConfirmar = document.getElementById('btn-confirmar');
    const btnCancelar = document.getElementById('btn-cancelar');

    let partidoSeleccionado = null; // Para guardar el partido que se seleccionó

// Mostrar modal con la info del partido
    function abrirModal(partido) {
        partidoSeleccionado = partido;

        const ahora = new Date();
        modalInfo.innerHTML = `
    <strong>${partido.equipoLocal}</strong> vs 
    <strong>${partido.equipoVisitante}</strong><br>
    Fecha actual: ${ahora.toLocaleDateString()}<br>
    Hora actual: ${ahora.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' })}
`;

        inputGL.value = partido.golesLocal ?? '';
        inputGV.value = partido.golesVisitante ?? '';
        modal.style.display = 'flex';
    }

// Cerrar modal
    function cerrarModal() {
        modal.style.display = 'none';
        partidoSeleccionado = null;
        inputGL.value = '';
        inputGV.value = '';
    }

// Cuando se hace clic en "Listo"
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
                fechaPronostico: new Date().toISOString().split('T')[0], // yyyy-mm-dd
                horaPronostico: new Date().toTimeString().split(' ')[0], // HH:MM:SS
            })
        })
            .then(res => {
                if (!res.ok) throw new Error("Error al registrar el pronóstico.");
                return res.json();
            })
            .then(res => {
                alert("¡Pronóstico registrado exitosamente!");
                cerrarModal();
                location.reload(); // recargar para reflejar cambios
            })
            .catch(err => {
                alert(err.message);
            });
    });

// Cuando se hace clic en "Cancelar"
    btnCancelar.addEventListener('click', cerrarModal);


});



