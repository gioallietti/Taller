package com.example.Taller.Service;

import com.example.Taller.Entity.IngresoRepuestoEntity;
import com.example.Taller.Repository.IngresoRepuestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngresoRepuestoServiceImpl implements IngresoRepuestoService {
    @Autowired
    private IngresoRepuestoRepository ingresoRepuestoRepository;

    public List<IngresoRepuestoEntity> obtenerIngresoRepuestoPorIngresoId (Integer IngresoId) {
        return ingresoRepuestoRepository.findAllByIngreso_Id(IngresoId);
    }

    public IngresoRepuestoEntity obtenerIngresoRepuestoPorIngresoId_RepuestoId (Integer ingresoId, Integer repuestoId) {
        return ingresoRepuestoRepository.findByIngreso_IdAndRepuestoId(ingresoId, repuestoId);
    }
}
