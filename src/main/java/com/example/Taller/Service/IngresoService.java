package com.example.Taller.Service;

import com.example.Taller.DTO.IngreosPorMesAnioDTO;
import com.example.Taller.Entity.EstadoEntity;
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

    IngresoEntity actualizarIngresoEstado(int id, EstadoEntity estado);

    IngresoEntity guardarMensaje(int id, IngresoEntity ingreso);

    List<IngresoEntity> findAllByRegistradoPor_Id(Integer registradoPorId);

    List<IngresoEntity> findAllByTecnicos(Integer registradoPorId);

    List<IngresoEntity> ingresoPorEstado_IdTipoUsuario(Integer estadoId, Integer tipoUsuarioId);

    List<IngresoEntity> IngresosFinalizadosMasTresMeses();

    List<IngresoEntity> listarIngresosNoFinalizados();

    List<IngresoEntity> listarIngresosFinalizados();

    List<IngresoEntity> obtenerIngresosFinalizados();

    List<IngreosPorMesAnioDTO> obtenerIngresosPorAnio();

    List<IngreosPorMesAnioDTO> obtenerIngresosPorMes();
}

