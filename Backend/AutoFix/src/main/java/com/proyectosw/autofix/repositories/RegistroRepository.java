package com.proyectosw.autofix.repositories;

import com.proyectosw.autofix.entities.RegistroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroRepository extends JpaRepository<RegistroEntity, Long> {

}
