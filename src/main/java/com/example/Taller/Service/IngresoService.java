package com.example.Taller.Service;

import com.example.Taller.Entity.IngresoEntity;

import java.util.List;

public interface IngresoService {
    IngresoEntity guardarIngreso(IngresoEntity ingreso);

    IngresoEntity obtenerIngresoPorId(int id);

    List<IngresoEntity> obtenerTodosLosIngresos();

    String eliminarIngreso(int id);
}
