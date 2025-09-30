package com.example.Taller.Service;

import com.example.Taller.Entity.EstadoEntity;

import java.util.List;

public interface EstadoService {

    EstadoEntity guardarEstado(EstadoEntity estado);

    EstadoEntity obtenerEstadoPorId(int id);

    List<EstadoEntity> obtenerTodosLosEstados();

    String eliminarEstado(int id);

    EstadoEntity actualizarEstado(int id, EstadoEntity estado);

}
