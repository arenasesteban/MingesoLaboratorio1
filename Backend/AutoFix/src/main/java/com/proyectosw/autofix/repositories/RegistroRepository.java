package com.proyectosw.autofix.repositories;

import com.proyectosw.autofix.entities.RegistroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RegistroRepository extends JpaRepository<RegistroEntity, Long> {
    RegistroEntity findByIdRegistro(Long idRegistro);

    List<RegistroEntity> findByPatenteAndFechaSalidaAfter(String patente, LocalDate fechaInicio);

    @Query("SELECT r.patente FROM RegistroEntity r WHERE r.idRegistro = :idRegistro")
    String findPatenteByIdRegistro(Long idRegistro);

    List<RegistroEntity> findRegistroEntitiesByPatente(String patente);
}
