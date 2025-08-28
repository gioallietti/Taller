package com.example.Taller.Service;

import com.example.Taller.Entity.RepuestoEntity;
import com.example.Taller.Repository.RepuestoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepuestoServiceImpl implements RepuestoService{

    @Autowired
    private RepuestoRepository repuestoRepository;

    @Override
    public RepuestoEntity guardarRepuesto(RepuestoEntity repuesto) {

        if(repuesto.getModelo().length() > 30){
            throw new IllegalArgumentException("EL nombre del modelo demasiado largo");
        }

        return repuestoRepository.save(repuesto);
    }

    @Override
    public RepuestoEntity actualizarRepuesto(RepuestoEntity repuesto){
        try {
            return repuestoRepository.save(repuesto);
        } catch (RuntimeException e) {
            return null;
        }
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

    public void updateCantidadCopias(int id, int cantidad) {
        Optional<RepuestoEntity> repuesto = repuestoRepository.findById(id);
        if (repuesto.isPresent()) {
            repuesto.get().setCantidad(repuesto.get().getCantidad() - cantidad);
            repuestoRepository.save(repuesto.get());
        }
    }
}
