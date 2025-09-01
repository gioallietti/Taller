package com.example.Taller.Repository;

import com.example.Taller.Entity.MarcaEntity;
import com.example.Taller.Entity.TipoEquipoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipoEquipoRepository extends JpaRepository<TipoEquipoEntity, Integer> {
    TipoEquipoEntity findByNombre(String nombre);

    List<TipoEquipoEntity> findAllByActivoTrue();

}
