package com.example.Taller.Service;

import com.example.Taller.Entity.UsuarioEntity;
import com.example.Taller.Repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UsuarioEntity guardarUsuario(UsuarioEntity usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public UsuarioEntity actualizarUsuario(UsuarioEntity usuario){
        try {
            String id = usuario.getId().toString();
            UsuarioEntity usuarioExiste = this.obtenerUsuarioPorId(id);

            if (usuarioExiste != null) {
                usuarioExiste.setNombre(usuario.getNombre());
                usuarioExiste.setApellido(usuario.getApellido());
                usuarioExiste.setCedula(usuario.getCedula());
                usuarioExiste.setTelefono(usuario.getTelefono());
                usuarioExiste.setEmail(usuario.getEmail());
                usuarioExiste.setTipoUsuario(usuario.getTipoUsuario());

                return usuarioRepository.save(usuarioExiste);
            } else {
                return null;
            }
        } catch (RuntimeException e) {
            return null;
        }
    }


    @Override
    public boolean obtenerUsuarioPorEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    @Override
    public List<UsuarioEntity> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public List<UsuarioEntity> obtenerTodosLosTecnicos(Integer tipoUsuarioId) {
        return usuarioRepository.findAllByTipoUsuario_Id(tipoUsuarioId);
    }

    @Override
    public boolean eliminarUsuario(String id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public UsuarioEntity obtenerUsuarioPorId(String id) {
        return (UsuarioEntity) this.usuarioRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Tipo de usuario encontrado con id: " + id));
    }

    @Override
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
