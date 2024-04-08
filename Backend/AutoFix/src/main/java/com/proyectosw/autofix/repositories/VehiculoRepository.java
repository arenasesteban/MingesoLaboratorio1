package com.proyectosw.autofix.repositories;

import com.proyectosw.autofix.entities.VehiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiculoRepository extends JpaRepository<VehiculoEntity, String> {

    VehiculoEntity findByPatente(String patente);

    @Query("SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END FROM VehiculoEntity v WHERE v.patente = :patente AND v.tipoMotor = :tipoMotor")
    int countTipoMotor(String patente, String tipoMotor);

    @Query("SELECT DISTINCT v.marca FROM VehiculoEntity v")
    List<String> findDistinctMarca();

    @Query("SELECT v.patente FROM VehiculoEntity v WHERE v.marca = :marca")
    List<String> findPatenteByMarca(String marca);
}
