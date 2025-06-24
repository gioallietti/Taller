package com.example.Taller.Service;

import com.example.Taller.Entity.UsuarioEntity;
import com.example.Taller.Repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService{
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UsuarioEntity guardarUsuario(UsuarioEntity usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public UsuarioEntity obtenerUsuarioPorEmail(String email) {
        return usuarioRepository.findById(email)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con email: " + email));
    }

    @Override
    public List<UsuarioEntity> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public String eliminarUsuario(String email) {
        if (!usuarioRepository.existsById(email)) {
            throw new EntityNotFoundException("El usuario con email " + email + " no existe");
        }
        usuarioRepository.deleteById(email);
        return "Usuario eliminado con éxito";
    }

    public UsuarioEntity login(UsuarioEntity usuarioEntity) throws BadRequestException {
        try {
            return usuarioRepository.findByEmailAndPassword(
                    usuarioEntity.getEmail(),
                    usuarioEntity.getPassword()
            );
        } catch (RuntimeException e) {
            throw new BadRequestException("Credenciales incorrectas");
        }
    }
}
