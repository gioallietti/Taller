package com.example.Taller.Service;

import com.example.Taller.Entity.MarcaEntity;
import com.example.Taller.Repository.MarcaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarcaServiceImpl implements MarcaService{
    @Autowired
    private MarcaRepository marcaRepository;

    public MarcaEntity guardarMarca(MarcaEntity marca) {
        return (MarcaEntity) this.marcaRepository.save(marca);
    }

    public MarcaEntity obtenerMarcaPorId(int id) {
        return (MarcaEntity) this.marcaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Marca encontrada con id: " + id));
    }

    public List<MarcaEntity> obtenerTodasLasMarcas() {
        return this.marcaRepository.findAll();
    }

    public String eliminarMarca(int id) {
        if (!this.marcaRepository.existsById(id)) {
            throw new EntityNotFoundException("La marca con id " + id + " no existe");
        } else {
            this.marcaRepository.deleteById(id);
            return "Marca eliminada con éxito";
        }
    }
}
