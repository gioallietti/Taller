package com.example.Taller.Service;

import com.example.Taller.Entity.PersonaEntity;
import com.example.Taller.Repository.PersonaRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl implements PersonaService{
    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public PersonaEntity guardarPersona(PersonaEntity persona) {
        return personaRepository.save(persona);
    }

    @Override
    public PersonaEntity obtenerPersonaPorId(int id) {
        return personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada con id: " + id));
    }

    @Override
    public List<PersonaEntity> obtenerTodasLasPersonas() {
        return personaRepository.findAll();
    }

    @Override
    public String eliminarPersona(int id) {
        if (!personaRepository.existsById(id)) {
            throw new EntityNotFoundException("La persona con id " + id + " no existe");
        }
        personaRepository.deleteById(id);
        return "Persona eliminada con éxito";
    }
}
