package com.example.Taller.Service;

import com.example.Taller.Entity.EquipoEntity;
import java.util.List;

public interface EquipoService {

    EquipoEntity guardarEquipo(EquipoEntity equipo);

    EquipoEntity obtenerEquipoPorId(int id);

    List<EquipoEntity> obtenerTodosLosEquipos();

    String eliminarEquipo(int id);

    List<EquipoEntity> listarEquiposPorTipoEquipo(int tipoEquipoId);

    EquipoEntity actualizarEquipo(int id, EquipoEntity equipo);

}
