document.addEventListener("DOMContentLoaded", () => {
    const tbody = document.getElementById('partidos-table-body');

    fetch('/api/partidos', { credentials: 'include' })
        .then(res => {
            if (!res.ok) {
                throw new Error('No se pudo cargar la lista de partidos.');
            }
            return res.json();
        })
        .then(data => {
            tbody.innerHTML = '';

            if (!data || data.length === 0) {
                const row = document.createElement('tr');
                const cell = document.createElement('td');
                cell.colSpan = 9;
                cell.textContent = 'No hay partidos registrados.';
                row.appendChild(cell);
                tbody.appendChild(row);
                return;
            }

            data.forEach(p => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${p.equipoLocal}</td>
                    <td>${p.equipoVisitante}</td>
                    <td>${p.estadoPartido}</td>
                    <td>${p.fechaPartido}</td>
                    <td>${p.horaPartido}</td>
                    <td>${p.golesLocal}</td>
                    <td>${p.golesVisitante}</td>
                    <td>${p.torneoNombre}</td>
                    <td>
                        <button class="btn-finalizar btn btn-secondary"
                                data-id="${p.partidoId}"
                                ${p.estadoPartido === 'Finalizado' ? 'disabled' : ''}>
                            ${p.estadoPartido === 'Finalizado' ? 'Finalizado' : 'Finalizar'}
                        </button>
                    </td>
                `;
                tbody.appendChild(row);
            });
        })
        .catch(error => {
            console.error('Error al obtener partidos:', error);
            tbody.innerHTML = `<tr><td colspan="9">Error al cargar los partidos.</td></tr>`;
        });


    tbody.addEventListener('click', (e) => {
        if (e.target && e.target.matches('.btn-finalizar')) {
            const button = e.target;
            const partidoId = button.getAttribute('data-id');

            fetch('/api/partidos/finalizar', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                credentials: 'include',
                body: JSON.stringify({ id: partidoId })
            })
                .then(res => {
                    if (!res.ok) throw new Error('Error al finalizar el partido');
                    return res.json();
                })
                .then(data => {
                    alert("Partido finalizado exitosamente.");

                    const row = button.closest('tr');
                    row.children[2].textContent = 'Finalizado';
                    row.children[5].textContent = data.golesLocal;
                    row.children[6].textContent = data.golesVisitante;

                    button.disabled = true;
                    button.textContent = 'Finalizado';
                })
                .catch(err => {
                    console.error("Error:", err);
                    alert("Ocurrió un error al finalizar el partido.");
                });
        }
    });

});
