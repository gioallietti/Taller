package com.example.Taller.Repository;

import com.example.Taller.Entity.TipoEquipoEntity;
import com.example.Taller.Entity.TipoUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoUsuarioRepository extends JpaRepository<TipoUsuarioEntity, Integer> {
}
