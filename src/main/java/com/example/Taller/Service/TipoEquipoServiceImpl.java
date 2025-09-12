package com.example.Taller.Service;

import com.example.Taller.Entity.MarcaEntity;
import com.example.Taller.Entity.TipoEquipoEntity;
import com.example.Taller.Repository.TipoEquipoRepository;
import java.util.List;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoEquipoServiceImpl implements TipoEquipoService {
    @Autowired
    private TipoEquipoRepository tipoEquipoRepository;

    public TipoEquipoEntity guardarTipoEquipo(TipoEquipoEntity tipoEquipo) {
        if (tipoEquipo.getNombre().length() < 2 || tipoEquipo.getNombre().length() > 25) {
            throw new IllegalArgumentException("El tipo de equipo debe tener entre 2 y 25 caracteres");
        }
        TipoEquipoEntity tipoEquipoExistente = tipoEquipoRepository.findByNombre(tipoEquipo.getNombre());
        if (tipoEquipoExistente != null) {
            if (Boolean.FALSE.equals(tipoEquipoExistente.getActivo())) {
                tipoEquipoExistente.setActivo(true);
                return tipoEquipoRepository.save(tipoEquipoExistente);
            } else {
                throw new IllegalArgumentException("Ya existe un tipo de equipo activo con ese nombre.");
            }
        }
        tipoEquipo.setActivo(true);
        return tipoEquipoRepository.save(tipoEquipo);
    }

    public TipoEquipoEntity obtenerTipoEquipoPorId(int id) {
        return (TipoEquipoEntity) this.tipoEquipoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tipo equipo encontrado con id: " + id));
    }

    public List<TipoEquipoEntity> obtenerTodosLosTipoEquipos() {
        return this.tipoEquipoRepository.findAllByActivoTrue();
    }

    public String eliminarTipoEquipo(int id) {
        TipoEquipoEntity tipoEquipo = this.tipoEquipoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El tipo de equipo con id " + id + " no existe"));
        tipoEquipo.setActivo(false);
        this.tipoEquipoRepository.save(tipoEquipo);
        return "Tipo de equipo eliminado lógicamente con éxito";
    }

    @Override
    public TipoEquipoEntity actualizarTipoEquipo(int id, TipoEquipoEntity tipoEquipo) {
        if (!tipoEquipoRepository.existsById(id)) {
            throw new EntityNotFoundException("El tipo de equipo con id " + id + " no existe");
        }
        tipoEquipo.setId(id);
        return tipoEquipoRepository.save(tipoEquipo);
    }
}
