package com.example.Taller.Repository;

import com.example.Taller.Entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, String> {
    UsuarioEntity findByEmailAndPassword(String email, String password);

}
