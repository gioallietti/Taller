package com.example.Taller.Service;

import com.example.Taller.Entity.PersonaEntity;

import java.util.List;

public interface PersonaService {

    PersonaEntity guardarPersona(PersonaEntity persona);

    PersonaEntity obtenerPersonaPorId(int id);

    List<PersonaEntity> obtenerTodasLasPersonas();

    String eliminarPersona(int id);
}
