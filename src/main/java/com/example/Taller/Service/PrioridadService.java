package com.example.Taller.Service;

import com.example.Taller.Entity.PrioridadEntity;

import java.util.List;

public interface PrioridadService {

    PrioridadEntity guardarPrioridad(PrioridadEntity prioridad);

    PrioridadEntity obtenerPrioridadPorId(int id);

    List<PrioridadEntity> obtenerTodasLasPrioridades();

    String eliminarPrioridad(int id);
}
