package com.example.Taller.Repository;

import com.example.Taller.Entity.MarcaEntity;
import com.example.Taller.Entity.RepuestoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepuestoRepository extends JpaRepository<RepuestoEntity, Integer> {

    List<RepuestoEntity> findAllByActivoTrue();

}
