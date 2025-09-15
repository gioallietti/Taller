package com.example.Taller.Service;

import com.example.Taller.Entity.PaisEntity;
import com.example.Taller.Repository.PaisRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaisServiceImpl implements PaisService{
    @Autowired
    private PaisRepository paisRepository;

    public PaisEntity guardarPais(PaisEntity pais) {
        if (pais.getNombre().length() < 2 || pais.getNombre().length() > 30) {
            throw new IllegalArgumentException("El pais debe tener entre 2 y 30 letras");
        }

        PaisEntity paisExistente = paisRepository.findByNombre(pais.getNombre());

        if (paisExistente != null) {
            if (Boolean.FALSE.equals(paisExistente.getActivo())) {
                paisExistente.setActivo(true);
                return paisRepository.save(paisExistente);
            } else {
                throw new IllegalArgumentException("Ya existe un pais activo con ese nombre.");
            }
        }
        pais.setActivo(true);
        return paisRepository.save(pais);
    }

    public PaisEntity obtenerPaisPorId(int id) {
        return (PaisEntity) this.paisRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pais encontrado con id: " + id));
    }

    public List<PaisEntity> obtenerTodasLosPaises() {
        return this.paisRepository.findAllByActivoTrue();
    }

    public String eliminarPais(int id) {
        PaisEntity pais = this.paisRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El pais con id " + id + " no existe"));
        pais.setActivo(false);
        this.paisRepository.save(pais);
        return "Pais eliminado lógicamente con éxito";
    }


    @Override
    public PaisEntity actualizarPais(int id, PaisEntity pais) {
        if (!paisRepository.existsById(id)) {
            throw new EntityNotFoundException("El pais con id " + id + " no existe");
        }
        pais.setId(id);
        return paisRepository.save(pais);
    }
}
