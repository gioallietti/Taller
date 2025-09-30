package com.example.Taller.Service;

import com.example.Taller.Entity.IngresoRepuestoEntity;

import java.util.List;

public interface IngresoRepuestoService {

    List<IngresoRepuestoEntity> obtenerIngresoRepuestoPorIngresoId (Integer ingresoId);
}
