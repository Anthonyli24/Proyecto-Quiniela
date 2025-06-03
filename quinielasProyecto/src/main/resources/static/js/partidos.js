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
                cell.colSpan = 8;
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
                `;

                tbody.appendChild(row);
            });
        })
        .catch(error => {
            console.error('Error al obtener partidos:', error);
            tbody.innerHTML = `<tr><td colspan="8">Error al cargar los partidos.</td></tr>`;
        });
});
