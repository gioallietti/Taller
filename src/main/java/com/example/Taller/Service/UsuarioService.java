package com.example.Taller.Service;

import com.example.Taller.Entity.UsuarioEntity;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface UsuarioService {
    UsuarioEntity guardarUsuario(UsuarioEntity usuario);

    boolean obtenerUsuarioPorEmail(String email);

    List<UsuarioEntity> obtenerTodosLosUsuarios();

    String eliminarUsuario(String email);

    UsuarioEntity login(UsuarioEntity usuarioEntity) throws BadRequestException;

}
