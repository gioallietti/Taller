package com.example.Taller.Repository;

import com.example.Taller.Entity.PresupuestoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PresupuestoRepository extends JpaRepository<PresupuestoEntity, Integer> {

    Optional<PresupuestoEntity> findByIngreso_Id(Integer ingresoId);
}
