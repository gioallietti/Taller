package com.example.Taller.Service;

import com.example.Taller.Entity.TipoEquipoEntity;

import java.util.List;

public interface TipoEquipoService {
    TipoEquipoEntity guardarTipoEquipo(TipoEquipoEntity tipoEquipo);

    TipoEquipoEntity obtenerTipoEquipoPorId(int id);

    List<TipoEquipoEntity> obtenerTodosLosTipoEquipos();

    String eliminarTipoEquipo(int id);
}
