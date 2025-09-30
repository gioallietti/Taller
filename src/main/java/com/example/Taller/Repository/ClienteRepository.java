package com.example.Taller.Repository;

import com.example.Taller.Entity.ClienteEntity;
import com.example.Taller.Entity.MarcaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer> {
    ClienteEntity findByCedula(String cedula);

    List<ClienteEntity> findAllByActivoTrue();

}
