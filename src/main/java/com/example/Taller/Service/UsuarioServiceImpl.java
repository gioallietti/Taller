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
        UsuarioEntity usuarioEmail = usuarioRepository.findByEmail(email);
        if (usuarioEmail == null){
            throw new EntityNotFoundException("Usuario no encontrado con email: " + email);
        }else{
            return usuarioEmail;
        }
    }

    public UsuarioEntity obtenerUsuarioPorId(int id) {
        return (UsuarioEntity) this.usuarioRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Tipo de usuario encontrado con id: " + id));
    }

    @Override
    public List<UsuarioEntity> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public String eliminarUsuario(int id) {
        if (!usuarioRepository.existsById(id)) {
            throw new EntityNotFoundException("El usuario con id " + id + " no existe");
        }
        usuarioRepository.deleteById(id);
        return "Usuario eliminado con éxito";
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
