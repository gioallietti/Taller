package com.example.Taller.Repository;

import com.example.Taller.Entity.EstadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<EstadoEntity, Integer> {
}
