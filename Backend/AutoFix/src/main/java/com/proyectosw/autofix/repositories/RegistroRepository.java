package com.proyectosw.autofix.repositories;

import com.proyectosw.autofix.entities.RegistroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroRepository extends JpaRepository<RegistroEntity, Long> {
    RegistroEntity findByIdRegistro(Long idRegistro);

    // Debe contar el numero de reparaciones de los ultimos 12 meses
    @Query("SELECT COUNT(r) FROM RegistroEntity r WHERE r.patente = :patente")
    Integer contarPorPatente(String patente);
}
