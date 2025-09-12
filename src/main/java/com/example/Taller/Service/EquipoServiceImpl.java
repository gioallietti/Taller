package com.example.Taller.Service;

import com.example.Taller.Entity.EquipoEntity;
import com.example.Taller.Entity.MarcaEntity;
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
        if (equipo.getModelo() == null || equipo.getModelo().isEmpty() || equipo.getModelo().length() > 50) {
            throw new IllegalArgumentException("El modelo debe tener entre 1 y 50 caracteres");
        }

        EquipoEntity equipoExistente = equipoRepository.findByTipoEquipoAndMarcaAndModelo(
                equipo.getTipoEquipo(), equipo.getMarca(), equipo.getModelo());

        if (equipoExistente != null) {
            if (Boolean.FALSE.equals(equipoExistente.getActivo())) {
                equipoExistente.setActivo(true);
                return equipoRepository.save(equipoExistente);
            } else {
                throw new IllegalArgumentException("Ya existe un equipo activo con ese tipo, marca y modelo.");
            }
        }

        equipo.setActivo(true);
        return equipoRepository.save(equipo);
    }

    public EquipoEntity obtenerEquipoPorId(int id) {
        return (EquipoEntity) this.equipoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Equipo no encontrado con id: " + id));
    }

    public List<EquipoEntity> obtenerTodosLosEquipos() {
        return this.equipoRepository.findAllByActivoTrue();
    }

    public String eliminarEquipo(int id) {
        EquipoEntity equipo = this.equipoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El equipo con id " + id + " no existe"));
        equipo.setActivo(false);
        this.equipoRepository.save(equipo);
        return "Equipo eliminado lógicamente con éxito";
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
