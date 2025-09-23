package com.example.Taller.Service;

import com.example.Taller.DTO.UsuarioDTO;
import com.example.Taller.Entity.UsuarioEntity;
import org.apache.coyote.BadRequestException;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    UsuarioEntity guardarUsuario(UsuarioEntity usuario) throws BadRequestException;

    boolean obtenerUsuarioPorEmail(String email);

    List<UsuarioEntity> obtenerTodosLosUsuarios();

    List<UsuarioEntity> obtenerTodosLosTecnicos(Integer tipoUsuarioId);

    boolean eliminarUsuario(String id);

    UsuarioDTO login(UsuarioEntity usuarioEntity) throws BadRequestException;

    UsuarioEntity obtenerUsuarioPorId(String id);

    UsuarioEntity actualizarUsuario(UsuarioEntity usuario);
}
