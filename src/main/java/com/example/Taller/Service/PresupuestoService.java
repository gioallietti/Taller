package com.example.Taller.Service;

import com.example.Taller.Entity.PresupuestoEntity;

import java.util.List;

public interface PresupuestoService {
    PresupuestoEntity guardarPresupuesto(PresupuestoEntity presupuesto);

    PresupuestoEntity obtenerPresupuestoPorId(int id);

    List<PresupuestoEntity> obtenerTodosLosPresupuestos();

    String eliminarPresupuesto(int id);
}
