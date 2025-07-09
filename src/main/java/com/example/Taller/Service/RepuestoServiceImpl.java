package com.example.Taller.Service;

import com.example.Taller.Entity.RepuestoEntity;
import com.example.Taller.Repository.RepuestoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepuestoServiceImpl implements RepuestoService{

    @Autowired
    private RepuestoRepository repuestoRepository;

    @Override
    public RepuestoEntity guardarRepuesto(RepuestoEntity repuesto) {
        return repuestoRepository.save(repuesto);
    }

    @Override
    public RepuestoEntity obtenerRepuestoPorId(int id) {
        return repuestoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Repuesto no encontrado con id: " + id));
    }

    @Override
    public List<RepuestoEntity> obtenerTodosLosRepuestos() {
        return repuestoRepository.findAll();
    }

    @Override
    public String eliminarRepuesto(int id) {
        if (!repuestoRepository.existsById(id)) {
            throw new EntityNotFoundException("El repuesto con id " + id + " no existe");
        }
        repuestoRepository.deleteById(id);
        return "Repuesto eliminado con éxito";
    }

    @Override
    public RepuestoEntity actualizarCantidadRepuesto(int id, int cantidad){
        RepuestoEntity repuesto = repuestoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Repuesto no encontrado con id: " + id));
        int nuevaCantidad = repuesto.getCantidad() + cantidad;
        repuesto.setCantidad(nuevaCantidad);
        return repuestoRepository.save(repuesto);
    }
}
