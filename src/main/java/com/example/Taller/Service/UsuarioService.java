package com.example.Taller.Service;

import com.example.Taller.Entity.UsuarioEntity;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface UsuarioService {
    UsuarioEntity guardarUsuario(UsuarioEntity usuario);

    UsuarioEntity obtenerUsuarioPorEmail(String email);

    List<UsuarioEntity> obtenerTodosLosUsuarios();

    String eliminarUsuario(int id);

    UsuarioEntity login(UsuarioEntity usuarioEntity) throws BadRequestException;

    UsuarioEntity obtenerUsuarioPorId(int id);

    UsuarioEntity actualizarUsuario(int id, UsuarioEntity usuario);
}
