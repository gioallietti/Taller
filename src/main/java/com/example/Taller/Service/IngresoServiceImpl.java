package com.example.Taller.Service;

import com.example.Taller.Entity.IngresoEntity;
import com.example.Taller.Repository.IngresoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class IngresoServiceImpl implements IngresoService{
    @Autowired
    private IngresoRepository ingresoRepository;

    @Override
    public IngresoEntity guardarIngreso(IngresoEntity ingreso) {
        return ingresoRepository.save(ingreso);
    }

    @Override
    public IngresoEntity obtenerIngresoPorId(int id) {
        return ingresoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ingreso no encontrado con id: " + id));
    }

    @Override
    public List<IngresoEntity> obtenerTodosLosIngresos() {
        return ingresoRepository.findAll();
    }

    @Override
    public String eliminarIngreso(int id) {
        if (!ingresoRepository.existsById(id)) {
            throw new EntityNotFoundException("El ingreso con id " + id + " no existe");
        }
        ingresoRepository.deleteById(id);
        return "Ingreso eliminado con éxito";
    }

    @Override
    public List<IngresoEntity> listarPorPrioridad(){
        return ingresoRepository.findAllByOrderByPrioridadAsc();
    }

    @Override
    public List<IngresoEntity> ingresosPorFechas(LocalDate desde, LocalDate hasta) {
        return this.ingresoRepository.findByFechaIngresoBetween(desde, hasta);
    }

    @Override
    public List<IngresoEntity> findAllByRegistradoPor_Id(Integer registradoPorId) {
        return this.ingresoRepository.findAllByRegistradoPor_Id(registradoPorId);
    }

    @Override
    public List<IngresoEntity> ingresoPorEstado_IdTipoUsuario(Integer estadoId, Integer tipoUsuarioId) {
        return this.ingresoRepository.findAllByEstado_IdAndReparadoPor_TipoUsuario_Id(estadoId, tipoUsuarioId);
    }

    @Override
    public IngresoEntity actualizarIngreso(int id, IngresoEntity ingreso) {
        if (!ingresoRepository.existsById(id)) {
            throw new EntityNotFoundException("El ingreso con id " + id + " no existe");
        }
        ingreso.setId(id);
        return ingresoRepository.save(ingreso);
    }
}
