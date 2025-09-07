package com.example.Taller.Service;

import com.example.Taller.DTO.IngreosPorMesAnioDTO;
import com.example.Taller.DTO.PresupuestosPorMesAnioDTO;
import com.example.Taller.Entity.PresupuestoEntity;

import java.util.List;

public interface PresupuestoService {
    PresupuestoEntity guardarPresupuesto(PresupuestoEntity presupuesto);

    PresupuestoEntity obtenerPresupuestoPorId(int id);

    List<PresupuestoEntity> obtenerTodosLosPresupuestos();

    String eliminarPresupuesto(int id);

    PresupuestoEntity actualizarPresupuesto(int id, PresupuestoEntity presupuesto);

    PresupuestoEntity obtenerPresupuestoPorIngresoId(Integer ingresoId);

    double calcularGanancia(PresupuestoEntity presupuesto);

    double calcularGananciaMensual(int anio, int mes);

    double calcularGananciaAnual(int anio);

    List<PresupuestosPorMesAnioDTO> obtenerPresupuestosPorAnio();

    List<PresupuestosPorMesAnioDTO> obtenerPresupuestosPorMes();

}
