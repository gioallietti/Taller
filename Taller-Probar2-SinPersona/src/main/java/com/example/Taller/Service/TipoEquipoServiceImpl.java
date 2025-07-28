package com.example.Taller.Service;

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
        return (TipoEquipoEntity) this.tipoEquipoRepository.save(tipoEquipo);
    }

    public TipoEquipoEntity obtenerTipoEquipoPorId(int id) {
        return (TipoEquipoEntity) this.tipoEquipoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tipo equipo encontrado con id: " + id));
    }

    public List<TipoEquipoEntity> obtenerTodosLosTipoEquipos() {
        return this.tipoEquipoRepository.findAll();
    }

    public String eliminarTipoEquipo(int id) {
        if (!this.tipoEquipoRepository.existsById(id)) {
            throw new EntityNotFoundException("El tipo de equipo con id " + id + " no existe");
        } else {
            this.tipoEquipoRepository.deleteById(id);
            return "Tipo de equipo eliminado con éxito";
        }
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
