package com.example.Taller.Service;

import com.example.Taller.Entity.TipoUsuarioEntity;

import java.util.List;

public interface TipoUsuarioService {
    TipoUsuarioEntity guardarTipoUsuario(TipoUsuarioEntity tipoUsuario);

    TipoUsuarioEntity obtenerTipoUsuarioPorId(int id);

    List<TipoUsuarioEntity> obtenerTodosLosTipoUsuarios();

    String eliminarTipoUsuario(int id);
}
