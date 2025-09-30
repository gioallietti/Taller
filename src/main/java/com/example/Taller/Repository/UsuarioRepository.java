package com.example.Taller.Repository;

import com.example.Taller.Entity.MarcaEntity;
import com.example.Taller.Entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, String> {
    UsuarioEntity findByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);

    List<UsuarioEntity> findAllByTipoUsuario_Id(Integer tipoUsuarioId);

    List<UsuarioEntity> findAllByActivoTrue();

    UsuarioEntity findByEmail(String email);

    UsuarioEntity findByCedula(String cedula);

}
