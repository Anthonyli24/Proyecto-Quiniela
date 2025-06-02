document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('btnBack').addEventListener('click', () => {
        window.location.href = '/jugador/quiniela-Ranking.html';
    });

    const params = new URLSearchParams(window.location.search);
    const quinielaId = params.get('quinielaId');

    fetch(`/api/inscripciones/quinielas/${quinielaId}/ranking`, { credentials: 'include' })
        .then(res => res.json())
        .then(ranking => {
            const tbody = document.getElementById('ranking-body');
            if (ranking.length === 0) {
                tbody.innerHTML = `<tr><td colspan="3" class="no-data">No hay participantes aún.</td></tr>`;
                return;
            }

            ranking.forEach((item, index) => {
                const row = document.createElement('tr');
                row.innerHTML = `
                        <td>${index + 1}</td>
                        <td>${item.usuario}</td>
                        <td>${item.puntaje}</td>
                    `;
                tbody.appendChild(row);
            });
        });
});