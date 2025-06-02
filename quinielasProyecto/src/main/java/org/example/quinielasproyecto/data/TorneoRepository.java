package org.example.quinielasproyecto.data;

import org.example.quinielasproyecto.logic.Entidades.Torneo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TorneoRepository extends JpaRepository<Torneo, Integer> {
}