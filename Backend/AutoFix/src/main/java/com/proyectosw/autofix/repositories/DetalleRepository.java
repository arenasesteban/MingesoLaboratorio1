package com.proyectosw.autofix.repositories;

import com.proyectosw.autofix.entities.DetalleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleRepository extends JpaRepository<DetalleEntity, Long> {

}
