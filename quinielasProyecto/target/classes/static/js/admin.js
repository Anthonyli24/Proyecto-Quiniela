document.addEventListener('DOMContentLoaded', () => {
    // 1) Construye la navbar con enlaces definidos aquí
    const links = [
        { label: 'Dashboard',       href: '/admin-dashboard.html' },
        { label: 'Quinielas',       href: '/admin/quinielas.html' },
        { label: 'Partidos',        href: '/admin/partidos.html' },
        { label: 'Usuarios',        href: '/admin/usuarios.html' },
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

    // 2) Obtiene el perfil para saludar al usuario
    fetch('/api/usuarios/perfil', { credentials: 'include' })
        .then(res => {
            if (!res.ok) throw new Error('No autorizado');
            return res.json();
        })
        .then(data => {
            // Nota: asegúrate de que LoginResponse incluya un campo nombreUsuario
            const nombre = data.nombreUsuario || 'Administrador';
            document.getElementById('welcome-message')
                .textContent = `Bienvenido, ${nombre}!`;
        })
        .catch(() => {
            // Si no está autenticado, regresamos al login
            //window.location.href = '/index.html';
        });
});

