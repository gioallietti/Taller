package com.example.Taller.Service;

import com.example.Taller.Entity.MarcaEntity;

import java.util.List;

public interface MarcaService {
    MarcaEntity guardarMarca(MarcaEntity marca);

    MarcaEntity obtenerMarcaPorId(int id);

    List<MarcaEntity> obtenerTodasLasMarcas();

    String eliminarMarca(int id);

    MarcaEntity actualizarMarca(int id, MarcaEntity marca);
}
