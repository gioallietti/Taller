package com.example.Taller.Repository;

import com.example.Taller.Entity.MarcaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarcaRepository extends JpaRepository<MarcaEntity, Integer> {
    MarcaEntity findByNombre(String nombre);
    List<MarcaEntity> findAllByActivoTrue();
}
