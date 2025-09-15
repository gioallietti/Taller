package com.example.Taller.Repository;

import com.example.Taller.Entity.PaisEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaisRepository extends JpaRepository <PaisEntity, Integer>{
    PaisEntity findByNombre(String nombre);
    List<PaisEntity> findAllByActivoTrue();

}
