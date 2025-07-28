package com.example.Taller.Service;

import com.example.Taller.Entity.PrioridadEntity;
import com.example.Taller.Repository.PrioridadRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrioridadServiceImpl implements PrioridadService{
    @Autowired
    private PrioridadRepository prioridadRepository;

    @Override
    public PrioridadEntity guardarPrioridad(PrioridadEntity prioridad) {
        return prioridadRepository.save(prioridad);
    }

    @Override
    public PrioridadEntity obtenerPrioridadPorId(int id) {
        return prioridadRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Prioridad no encontrada con id: " + id));
    }

    @Override
    public List<PrioridadEntity> obtenerTodasLasPrioridades() {
        return prioridadRepository.findAll();
    }

    @Override
    public String eliminarPrioridad(int id) {
        if (!prioridadRepository.existsById(id)) {
            throw new EntityNotFoundException("La prioridad con id " + id + " no existe");
        }
        prioridadRepository.deleteById(id);
        return "Prioridad eliminada con éxito";
    }

    @Override
    public PrioridadEntity actualizarPrioridad(int id, PrioridadEntity prioridad) {
        if (!prioridadRepository.existsById(id)) {
            throw new EntityNotFoundException("La prioridad con id " + id + " no existe");
        }
        prioridad.setId(id);
        return prioridadRepository.save(prioridad);
    }
}
