package com.example.Taller.Service;

import com.example.Taller.Entity.EquipoEntity;
import com.example.Taller.Entity.TipoEquipoEntity;
import com.example.Taller.Repository.EquipoRepository;
import com.example.Taller.Repository.TipoEquipoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipoServiceImpl implements EquipoService{
    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private TipoEquipoRepository tipoEquipoRepository;

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

    @Override
    public List<EquipoEntity> listarEquiposPorTipoEquipo(int tipoEquipoId) {
        TipoEquipoEntity tipoEquipo = this.tipoEquipoRepository.findById(tipoEquipoId).orElseThrow(() -> new EntityNotFoundException("Tipo de equipo no encontrado"));
        return this.equipoRepository.findByTipoEquipo(tipoEquipo);
    }

    @Override
    public EquipoEntity actualizarEquipo(int id, EquipoEntity equipo) {
        if (!equipoRepository.existsById(id)) {
            throw new EntityNotFoundException("El equipo con id " + id + " no existe");
        }
        equipo.setId(id);
        return equipoRepository.save(equipo);
    }
}
