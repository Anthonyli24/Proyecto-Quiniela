document.addEventListener('DOMContentLoaded', () => {
    const links = [
        { label: 'Dashboard',       href: '../jugador-dashboard.html' },
        { label: 'Inscripción',       href: '/jugador/inscripcion.html' },
        { label: 'Jugar',        href: '/jugador/jugar.html' },
        { label: 'Ranking',        href: '/jugador/ranking.html' },
        { label: 'Cerrar sesión',   href: '/api/usuarios/logout', apiCall: true }
    ];

    const nav = document.getElementById('navbar');
    links.forEach(link => {
        const a = document.createElement('a');
        a.textContent = link.label;
        if (link.apiCall) {
            a.href = '#';
            a.addEventListener('click', e => {
                e.preventDefault();
                fetch(link.href, { credentials: 'include' })
                    .then(() => window.location.href = '/index.html');
            });
        } else {
            a.href = link.href;
        }
        nav.appendChild(a);
    });

    fetch('/api/usuarios/perfil', { credentials: 'include' })
        .then(res => {
            if (!res.ok) throw new Error('No autorizado');
            return res.json();
        })
        .then(data => {
            const nombre = data.nombreUsuario || 'usuario';
            document.getElementById('welcome-message')
                .textContent = `Bienvenido, ${nombre}!`;
        })
        .catch(() => {
            //window.location.href = '/login/Login.html';
        });
});