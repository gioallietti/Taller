package com.example.Taller.Repository;

import com.example.Taller.Entity.IngresoRepuestoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IngresoRepuestoRepository extends JpaRepository<IngresoRepuestoEntity, Integer> {
    IngresoRepuestoEntity findByIngreso_Id(Integer ingresId);

    IngresoRepuestoEntity findByIngreso_IdAndRepuestoId(Integer ingresId, Integer RepuestoId);

    List<IngresoRepuestoEntity> findAllByIngreso_Id (Integer ingresId);
}
