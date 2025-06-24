package com.example.Taller.Service;

import com.example.Taller.Entity.PresupuestoEntity;
import com.example.Taller.Repository.PresupuestoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PresupuestoServiceImpl implements PresupuestoService{
    @Autowired
    private PresupuestoRepository presupuestoRepository;

    @Override
    public PresupuestoEntity guardarPresupuesto(PresupuestoEntity presupuesto) {
        return presupuestoRepository.save(presupuesto);
    }

    @Override
    public PresupuestoEntity obtenerPresupuestoPorId(int id) {
        return presupuestoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Presupuesto no encontrado con id: " + id));
    }

    @Override
    public List<PresupuestoEntity> obtenerTodosLosPresupuestos() {
        return presupuestoRepository.findAll();
    }

    @Override
    public String eliminarPresupuesto(int id) {
        if (!presupuestoRepository.existsById(id)) {
            throw new EntityNotFoundException("El presupuesto con id " + id + " no existe");
        }
        presupuestoRepository.deleteById(id);
        return "Presupuesto eliminado con éxito";
    }
}
