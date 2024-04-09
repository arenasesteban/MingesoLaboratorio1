package com.proyectosw.autofix.repositories;

import com.proyectosw.autofix.entities.ReparacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReparacionRespository extends JpaRepository<ReparacionEntity, Long> {
    List<ReparacionEntity> findByIdRegistro(Long idRegistro);

    ReparacionEntity findFirstByIdRegistro(Long idRegistro);

    @Query("SELECT r.idRegistro FROM ReparacionEntity r WHERE r.numeroReparacion = :numeroReparacion")
    List<Long> findIdRegistroByNumeroReparacion(Integer numeroReparacion);

    @Query("SELECT SUM(r.precio) FROM ReparacionEntity r WHERE r.numeroReparacion = :numeroReparacion")
    int sumPrecioByNumeroReparacion(Integer numeroReparacion);
}
