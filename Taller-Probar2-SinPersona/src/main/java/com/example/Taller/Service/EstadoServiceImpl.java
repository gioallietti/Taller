package com.example.Taller.Service;

import com.example.Taller.Entity.EstadoEntity;
import com.example.Taller.Repository.EstadoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoServiceImpl implements EstadoService{
    @Autowired
    private EstadoRepository estadoRepository;

    public EstadoEntity guardarEstado(EstadoEntity estado) {
        return (EstadoEntity) this.estadoRepository.save(estado);
    }

    public EstadoEntity obtenerEstadoPorId(int id) {
        return (EstadoEntity) this.estadoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Estado encontrado con id: " + id));
    }

    public List<EstadoEntity> obtenerTodosLosEstados() {
        return this.estadoRepository.findAll();
    }

    public String eliminarEstado(int id) {
        if (!this.estadoRepository.existsById(id)) {
            throw new EntityNotFoundException("El estado con id " + id + " no existe");
        } else {
            this.estadoRepository.deleteById(id);
            return "Estado eliminado con éxito";
        }
    }

    @Override
    public EstadoEntity actualizarEstado(int id, EstadoEntity estado) {
        if (!estadoRepository.existsById(id)) {
            throw new EntityNotFoundException("El estado con id " + id + " no existe");
        }
        estado.setId(id);
        return estadoRepository.save(estado);
    }
}
