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
                    <div class="btn-wrapper">
                        <button class="btn btn-primary btn-pronosticar" data-id="${q.id}">Pronosticar</button>
                    </div>
                `;
                div.querySelector('button').addEventListener('click', () => {
                    window.location.href = `/jugador/pronostico.html?quinielaId=${q.id}`;
                });
                container.appendChild(div);
            });
        });
});
