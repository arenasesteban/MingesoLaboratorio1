package com.proyectosw.autofix.repositories;

import com.proyectosw.autofix.entities.VehiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiculoRepository extends JpaRepository<VehiculoEntity, String> {

    VehiculoEntity findByPatente(String patente);
}
