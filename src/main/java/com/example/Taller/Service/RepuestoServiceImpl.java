package com.example.Taller.Service;

import com.example.Taller.Entity.MarcaEntity;
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

        if(repuesto.getStockMinimo() > repuesto.getCantidad()){
            throw new IllegalArgumentException("La cantidad no puede ser menos que la cantidad minima");
        }

        if(repuesto.getModelo().length() > 30){
            throw new IllegalArgumentException("EL nombre del modelo demasiado largo");
        }

        RepuestoEntity repuestoExistente = repuestoRepository.findByNombreAndMarcaAndModelo(
                repuesto.getNombre(), repuesto.getMarca(), repuesto.getModelo());

        if (repuestoExistente != null) {
            if (Boolean.FALSE.equals(repuestoExistente.getActivo())) {
                repuestoExistente.setActivo(true);
                repuestoExistente.setPrecio(repuesto.getPrecio());
                repuestoExistente.setDescripcion(repuesto.getDescripcion());
                repuestoExistente.setCantidad(repuesto.getCantidad());
                repuestoExistente.setFecha(repuesto.getFecha());
                repuestoExistente.setStockMinimo(repuesto.getStockMinimo());

                return repuestoRepository.save(repuestoExistente);
            } else {
                throw new IllegalArgumentException("Ya existe un repuesto activo con ese nombre, marca y modelo.");
            }
        }

        repuesto.setActivo(true);
        return repuestoRepository.save(repuesto);
    }

    /*@Override
    public RepuestoEntity guardarRepuesto(RepuestoEntity repuesto) {

        if(repuesto.getModelo().length() > 30){
            throw new IllegalArgumentException("EL nombre del modelo demasiado largo");
        }

        return repuestoRepository.save(repuesto);
    }*/

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
        return repuestoRepository.findAllByActivoTrue();
    }

    public String eliminarRepuesto(int id) {
        RepuestoEntity repuesto = this.repuestoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El reupuesto con id " + id + " no existe"));
        repuesto.setActivo(false);
        this.repuestoRepository.save(repuesto);
        return "Repuesto eliminado lógicamente con éxito";
    }

    public void updateCantidadCopias(int id, int cantidad) {
        Optional<RepuestoEntity> repuesto = repuestoRepository.findById(id);
        if (repuesto.isPresent()) {
            repuesto.get().setCantidad(repuesto.get().getCantidad() - cantidad);
            repuestoRepository.save(repuesto.get());
        }
    }
}
