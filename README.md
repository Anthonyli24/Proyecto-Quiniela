# Sistema de Quinielas de Fútbol 

Este proyecto es una implementación de un sistema de gestión de quinielas de fútbol, desarrollado como parte del curso de Diseño e Implementación de Bases de Datos (EIF211) de la Universidad Nacional de Costa Rica (UNA).

El sistema permite a los usuarios registrarse, inscribirse en quinielas, hacer pronósticos sobre partidos de fútbol y competir con otros jugadores según sus aciertos. Utiliza una base de datos relacional para manejar la información relacionada con usuarios, partidos, resultados, quinielas y puntuaciones.

## Integrantes del Proyecto
- Sebastián Álvarez Gómez
- Gabriel Chavarria Calero
- Bayron Vega Alvarez
- Andrey Hernandez Benavides  
- Anthony Li Perera  

## Funcionalidades

### Registro y Autenticación de Usuarios
- Registro de jugadores y administradores.
- Inicio de sesión con validación de credenciales.

### Gestión de Quinielas
- Creación y administración de quinielas.
- Configuración de reglas y fechas límite de inscripción.
- Inscripción de jugadores en quinielas activas.

### Administración de Partidos
- Registro de partidos con información detallada: equipos, fecha y hora.
- Actualización de resultados tras la finalización del partido.

### Pronósticos de Jugadores
- Los jugadores pueden realizar predicciones antes del inicio del partido.
- Predicción de marcadores para cada equipo.

### Cálculo de Puntos
- Sistema automático de puntuación basado en la exactitud del pronóstico:
  - 3 puntos por resultado exacto.
  - 1 punto por acertar el ganador sin resultado exacto.
  - 0 puntos si no hay coincidencia.

### Tabla de Posiciones
- Generación de un ranking por quiniela con base en los puntajes acumulados.
- Visualización del historial de aciertos por jugador.


## Tecnologías Utilizadas
- **Base de Datos**: SQL Server  
- **Arquitectura**: Modelo-Vista-Controlador (MVC)  
- **Herramientas de modelado**:  SQL Server Management Studio / Draw.io  

## Consideraciones
- Enfocado en la práctica de modelado de datos, relaciones entre entidades, consultas SQL y procedimientos almacenados.
- Cumple con las prácticas de desarrollo en capas y separación de responsabilidades.
