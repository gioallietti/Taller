package com.example.Taller.Service;

import com.example.Taller.Entity.PaisEntity;

import java.util.List;

public interface PaisService {
    PaisEntity guardarPais(PaisEntity pais);

    PaisEntity obtenerPaisPorId(int id);

    List<PaisEntity> obtenerTodasLosPaises();

    String eliminarPais(int id);

    PaisEntity actualizarPais(int id, PaisEntity pais);
}
