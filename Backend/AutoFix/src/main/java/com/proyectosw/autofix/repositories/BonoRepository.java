package com.proyectosw.autofix.repositories;

import com.proyectosw.autofix.entities.BonoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BonoRepository extends JpaRepository<BonoEntity, Long> {
    BonoEntity findByMarca(String Marca);
}
