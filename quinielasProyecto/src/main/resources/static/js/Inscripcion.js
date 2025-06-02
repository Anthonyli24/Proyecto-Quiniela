document.addEventListener('DOMContentLoaded', () => {
    const container = document.getElementById('quinielas-container');
    const apiUrl = '/api/inscripciones/disponibles';

    fetch(apiUrl, { credentials: 'include' })
        .then(res => {
            if (!res.ok) throw new Error('Error al cargar quinielas');
            return res.json();
        })
        .then(quinielas => {
            container.innerHTML = '';
            const activas = quinielas.filter(q => q.estado === 'Activa');

            if (activas.length === 0) {
                container.innerHTML = '<p>No hay quinielas activas.</p>';
                return;
            }

            activas.forEach(q => {
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
                        Inscripción
                    </button>
                `;
                container.appendChild(card);
            });

            container.querySelectorAll('.btn-ver').forEach(btn => {
                btn.addEventListener('click', e => {
                    const quinielaId = parseInt(e.currentTarget.getAttribute('data-id'));

                    fetch('/api/usuarios/perfil', { credentials: 'include' })
                        .then(res => {
                            if (!res.ok) throw new Error('No autenticado');
                            return res.json();
                        })
                        .then(user => {
                            const usuarioId = user.usuarioId;

                            return fetch('/api/inscripciones/registrar', {
                                method: 'POST',
                                headers: {
                                    'Content-Type': 'application/json'
                                },
                                credentials: 'include',
                                body: JSON.stringify({ quinielaId: quinielaId, usuarioId: usuarioId })
                            });
                        })
                        .then(res => {
                            if (!res.ok) throw new Error("Error al inscribirse");
                            return res.text();
                        })
                        .then(msg => alert(msg))
                        .catch(err => alert(err.message));
                });

            });
        })
        .catch(err => {
            container.innerHTML = `<p class="error">${err.message}</p>`;
        });

    function formatDate(dstr) {
        const d = new Date(dstr);
        return d.toLocaleDateString();
    }
});
