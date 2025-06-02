package org.example.quinielasproyecto.data;

import org.example.quinielasproyecto.logic.Entidades.Partido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartidoJpaRepository extends JpaRepository<Partido, Long> {
}
