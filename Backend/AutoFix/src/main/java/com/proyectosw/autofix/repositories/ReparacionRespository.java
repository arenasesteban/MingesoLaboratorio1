package com.proyectosw.autofix.repositories;

import com.proyectosw.autofix.entities.ReparacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReparacionRespository extends JpaRepository<ReparacionEntity, Long> {

    List<ReparacionEntity> findByIdRegistro(Long idRegistro);
}
