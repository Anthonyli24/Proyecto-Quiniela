document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("torneoForm");
    const mensajeDiv = document.getElementById("mensaje");

    form.addEventListener("submit", function(e) {
        e.preventDefault();
        mensajeDiv.textContent = "";

        const nombre = document.getElementById("torneo_nombre").value;
        const fechaInicio = document.getElementById("fecha_inicio").value;
        const fechaCierre = document.getElementById("fecha_cierre").value;
        const resultadoFinal = document.getElementById("resultado_final").value;

        const params = new URLSearchParams();
        params.append("nombre", nombre);
        params.append("fechaInicio", fechaInicio);
        params.append("fechaCierre", fechaCierre);
        if (resultadoFinal) params.append("resultadoFinal", resultadoFinal);

        fetch("/api/torneos/registrar", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: params.toString()
        })
            .then(res => res.text().then(text => ({ ok: res.ok, status: res.status, text })))
            .then(({ ok, status, text }) => {
                mensajeDiv.textContent = text;
                mensajeDiv.style.color = ok ? "green" : "red";
                if (ok) {
                    form.reset();
                    setTimeout(() => {
                        window.location.href = "partidos.html";
                    }, 1000);
                }
            });
    });
});
