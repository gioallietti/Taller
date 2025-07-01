package com.example.Taller.Service;

import com.example.Taller.Entity.RepuestoEntity;

import java.util.List;

public interface RepuestoService {
    RepuestoEntity guardarRepuesto(RepuestoEntity repuesto);

    RepuestoEntity obtenerRepuestoPorId(int id);

    List<RepuestoEntity> obtenerTodosLosRepuestos();

    String eliminarRepuesto(int id);

    RepuestoEntity actualizarRepuesto(int id, RepuestoEntity repuesto);
}
