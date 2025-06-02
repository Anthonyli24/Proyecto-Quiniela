package org.example.quinielasproyecto.logic.dto;

public record PronosticoResponseDTO(
        Long partidoId,
        int golesLocal,
        int golesVisitante,
        Long quinielaId,
        String fechaPronostico,
        String horaPronostico
) {}

