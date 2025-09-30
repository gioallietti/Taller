package com.example.Taller.Service;

import com.example.Taller.DTO.UsuarioDTO;
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
        UsuarioEntity usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());

        if (usuarioExistente != null) {
            if (Boolean.FALSE.equals(usuarioExistente.getActivo())) {
                usuarioExistente.setActivo(true);
                usuarioExistente.setPassword(usuario.getPassword());
                usuarioExistente.setNombre(usuario.getNombre());
                usuarioExistente.setApellido(usuario.getApellido());
                usuarioExistente.setTipoUsuario(usuario.getTipoUsuario());
                return usuarioRepository.save(usuarioExistente);
            } else {
                throw new IllegalArgumentException("Ya existe un usuario con ese email.");
            }
        }

        UsuarioEntity usuarioExistentePorCedula = usuarioRepository.findByCedula(usuario.getCedula());

        if (usuarioExistentePorCedula != null) {
            if (Boolean.FALSE.equals(usuarioExistentePorCedula.getActivo())) {
                usuarioExistentePorCedula.setActivo(true);
                usuarioExistentePorCedula.setPassword(usuario.getPassword());
                usuarioExistentePorCedula.setNombre(usuario.getNombre());
                usuarioExistentePorCedula.setApellido(usuario.getApellido());
                usuarioExistentePorCedula.setTipoUsuario(usuario.getTipoUsuario());
                usuarioExistentePorCedula.setEmail(usuario.getEmail());
                usuarioExistentePorCedula.setTelefono(usuario.getTelefono());
                return usuarioRepository.save(usuarioExistentePorCedula);
            } else {
                throw new IllegalArgumentException("Ya existe un usuario con esa cédula.");
            }
        }


        usuario.setActivo(true);
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
        return usuarioRepository.findAllByActivoTrue();
    }

    @Override
    public List<UsuarioEntity> obtenerTodosLosTecnicos(Integer tipoUsuarioId) {
        return usuarioRepository.findAllByTipoUsuario_Id(tipoUsuarioId);
    }

    @Override
    public boolean eliminarUsuario(String id) {
        UsuarioEntity usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El usuario con id " + id + " no existe"));
        usuario.setActivo(false);
        usuarioRepository.save(usuario);
        return true;
    }

    public UsuarioEntity obtenerUsuarioPorId(String id) {
        return (UsuarioEntity) this.usuarioRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Tipo de usuario encontrado con id: " + id));
    }

    /*@Override
    public UsuarioDTO login(UsuarioEntity usuarioEntity) throws BadRequestException {
        try {
            UsuarioEntity buscarUsuario = usuarioRepository.findByEmailAndPassword(
                    usuarioEntity.getEmail(),
                    usuarioEntity.getPassword()
            );
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            if(buscarUsuario != null) {
                usuarioDTO.setId(buscarUsuario.getId());
                usuarioDTO.setNombre(buscarUsuario.getNombre());
                usuarioDTO.setApellido(buscarUsuario.getApellido());
                usuarioDTO.setEmail(buscarUsuario.getEmail());
                usuarioDTO.setTipoUsuario(buscarUsuario.getTipoUsuario());
            }
            return usuarioDTO;

        } catch (RuntimeException e) {
            throw new BadRequestException("Credenciales incorrectas");
        }
    }*/


    @Override
public UsuarioDTO login(UsuarioEntity usuarioEntity) {
    if (usuarioEntity.getEmail() == null || usuarioEntity.getPassword() == null) {
        throw new IllegalArgumentException("Email y contraseña son obligatorios.");
    }

    UsuarioEntity buscarUsuario = usuarioRepository.findByEmailAndPassword(
            usuarioEntity.getEmail(),
            usuarioEntity.getPassword()
    );

    if (buscarUsuario == null || Boolean.FALSE.equals(buscarUsuario.getActivo())) {
        throw new IllegalArgumentException("Email o contraseña incorrectos.");
    }

    UsuarioDTO usuarioDTO = new UsuarioDTO();
    usuarioDTO.setId(buscarUsuario.getId());
    usuarioDTO.setNombre(buscarUsuario.getNombre());
    usuarioDTO.setApellido(buscarUsuario.getApellido());
    usuarioDTO.setEmail(buscarUsuario.getEmail());
    usuarioDTO.setTipoUsuario(buscarUsuario.getTipoUsuario());

    return usuarioDTO;
}

}
