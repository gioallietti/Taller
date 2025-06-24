package com.example.Taller.Service;

import com.example.Taller.Entity.TipoUsuarioEntity;
import com.example.Taller.Repository.TipoUsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoUsuarioServiceImpl implements TipoUsuarioService{
    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    public TipoUsuarioEntity guardarTipoUsuario(TipoUsuarioEntity tipoUsuario) {
        return (TipoUsuarioEntity) this.tipoUsuarioRepository.save(tipoUsuario);
    }

    public TipoUsuarioEntity obtenerTipoUsuarioPorId(int id) {
        return (TipoUsuarioEntity) this.tipoUsuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tipo de usuario encontrado con id: " + id));
    }

    public List<TipoUsuarioEntity> obtenerTodosLosTipoUsuarios() {
        return this.tipoUsuarioRepository.findAll();
    }

    public String eliminarTipoUsuario(int id) {
        if (!this.tipoUsuarioRepository.existsById(id)) {
            throw new EntityNotFoundException("El tipo de usuario con id " + id + " no existe");
        } else {
            this.tipoUsuarioRepository.deleteById(id);
            return "Tipo de usuario eliminado con éxito";
        }
    }

}
