package com.example.Taller.Repository;

import com.example.Taller.Entity.IngresoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface IngresoRepository extends JpaRepository<IngresoEntity, Integer> {
    List<IngresoEntity> findAllByOrderByPrioridadAsc();

    List<IngresoEntity> findByFechaIngresoBetween(LocalDate desde, LocalDate hasta);

    List<IngresoEntity> findAllByRegistradoPor_Id(Integer registradoPorId);

    List<IngresoEntity> findAllByEstado_IdAndReparadoPor_TipoUsuario_Id(Integer estadoId, Integer tipoUsuarioId);

}
