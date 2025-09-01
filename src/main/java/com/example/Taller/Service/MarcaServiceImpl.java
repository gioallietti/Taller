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

        if (marca.getNombre().length() < 2 || marca.getNombre().length() > 25 ){
            throw new IllegalArgumentException("La marca debe tener entre 2 y 25 caracteres");
        }

        if (marcaRepository.findByNombre(marca.getNombre()) != null){
            throw new IllegalArgumentException("Ya existe una marca con ese nombre.");
        }

        return (MarcaEntity) this.marcaRepository.save(marca);
    }
    public MarcaEntity obtenerMarcaPorId(int id) {
        return (MarcaEntity) this.marcaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Marca encontrada con id: " + id));
    }

    public List<MarcaEntity> obtenerTodasLasMarcas() {
        return this.marcaRepository.findAllByActivoTrue();
    }

    public String eliminarMarca(int id) {
        MarcaEntity marca = this.marcaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("La marca con id " + id + " no existe"));
        marca.setActivo(false);
        this.marcaRepository.save(marca);
        return "Marca eliminada lógicamente con éxito";
    }


    @Override
    public MarcaEntity actualizarMarca(int id, MarcaEntity marca) {
        if (!marcaRepository.existsById(id)) {
            throw new EntityNotFoundException("La marca con id " + id + " no existe");
        }
        marca.setId(id);
        return marcaRepository.save(marca);
    }
}
