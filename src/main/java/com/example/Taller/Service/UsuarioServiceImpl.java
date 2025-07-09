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
    public boolean obtenerUsuarioPorEmail(String email) {
        return usuarioRepository.existsById(email);

    }

    @Override
    public List<UsuarioEntity> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public boolean eliminarUsuario(Integer id) {
        return usuarioRepository.deleteById(id);
    }

    public UsuarioEntity login(UsuarioEntity usuarioEntity) throws BadRequestException {
        try {
            return usuarioRepository.findByEmailAndPassword(
                    usuarioEntity.getEmail(),
                    usuarioEntity.getPassword()
            );
        } catch (RuntimeException e) {
            throw new BadRequestException("Mail o contraseña incorrectas");
        }
    }
}
