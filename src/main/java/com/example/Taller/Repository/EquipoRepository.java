package com.example.Taller.Repository;

import com.example.Taller.Entity.EquipoEntity;
import com.example.Taller.Entity.TipoEquipoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipoRepository extends JpaRepository<EquipoEntity, Integer> {

    List<EquipoEntity> findByTipoEquipo(TipoEquipoEntity tipoEquipo);

}
