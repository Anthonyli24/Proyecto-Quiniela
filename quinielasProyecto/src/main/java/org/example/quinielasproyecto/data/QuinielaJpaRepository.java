package org.example.quinielasproyecto.data;

import org.example.quinielasproyecto.logic.Entidades.Quiniela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuinielaJpaRepository extends JpaRepository<Quiniela, Integer> {

}
