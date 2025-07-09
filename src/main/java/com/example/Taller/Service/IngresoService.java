package com.example.Taller.Service;

import com.example.Taller.Entity.IngresoEntity;

import java.time.LocalDate;
import java.util.List;

public interface IngresoService {
    IngresoEntity guardarIngreso(IngresoEntity ingreso);

    IngresoEntity obtenerIngresoPorId(int id);

    List<IngresoEntity> obtenerTodosLosIngresos();

    String eliminarIngreso(int id);

    List<IngresoEntity> listarPorPrioridad();

    List<IngresoEntity> ingresosPorFechas(LocalDate desde, LocalDate hasta);

    IngresoEntity actualizarIngreso(int id, IngresoEntity ingreso);
}

