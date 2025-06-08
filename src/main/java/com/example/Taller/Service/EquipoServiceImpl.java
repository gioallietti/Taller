package com.example.Taller.Service;

import com.example.Taller.Entity.EquipoEntity;
import com.example.Taller.Entity.TipoEquipoEntity;
import com.example.Taller.Repository.EquipoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipoServiceImpl implements EquipoService{
    @Autowired
    private EquipoRepository equipoRepository;

    public EquipoEntity guardarEquipo(EquipoEntity equipo) {
        return (EquipoEntity) this.equipoRepository.save(equipo);
    }

    public EquipoEntity obtenerEquipoPorId(int id) {
        return (EquipoEntity) this.equipoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Equipo no encontrado con id: " + id));
    }

    public List<EquipoEntity> obtenerTodosLosEquipos() {
        return this.equipoRepository.findAll();
    }

    public String eliminarEquipo(int id) {
        if (!this.equipoRepository.existsById(id)) {
            throw new EntityNotFoundException("El equipo con id " + id + " no existe");
        } else {
            this.equipoRepository.deleteById(id);
            return "Equipo eliminada con éxito";
        }
    }

    public List<EquipoEntity> buscarPorTipoEquipo(TipoEquipoEntity tipoEquipo) {
        return this.equipoRepository.findByTipoEquipo(tipoEquipo);
    }
}
