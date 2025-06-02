document.addEventListener('DOMContentLoaded', () => {
    fetch('/api/usuarios/perfil', { credentials: 'include' })
        .then(res => res.json())
        .then(usuario => {
            return fetch(`/api/inscripciones/usuarios/${usuario.usuarioId}/quinielas`, { credentials: 'include' });
        })
        .then(res => res.json())
        .then(quinielas => {

            const container = document.getElementById('quinielas-container');

            if (quinielas.length === 0) {
                container.innerHTML = '<p>No tiene quinielas inscritas.</p>';
                return;
            }

            quinielas.forEach(q => {
                const div = document.createElement('div');
                div.className = 'card';
                div.innerHTML = `
                        <h3 class="card-title">${q.nombre}</h3>
                        <button class="btn btn-primary" data-id="${q.id}">Ver Ranking</button>
                    `;
                div.querySelector('button').addEventListener('click', () => {
                    window.location.href = `/jugador/ranking.html?quinielaId=${q.id}`;
                });
                container.appendChild(div);
            });
        });
});