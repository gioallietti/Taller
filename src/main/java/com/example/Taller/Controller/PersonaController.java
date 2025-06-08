package com.example.Taller.Controller;

import com.example.Taller.Entity.PersonaEntity;
import com.example.Taller.Service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/personas")
public class PersonaController {
    @Autowired
    private PersonaService personaService;

    @PostMapping("/crea")
    public ResponseEntity<PersonaEntity> agregarPersona(@RequestBody PersonaEntity persona) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personaService.guardarPersona(persona));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaEntity> obtenerPersona(@PathVariable int id) {
        return ResponseEntity.ok(personaService.obtenerPersonaPorId(id));
    }

    @GetMapping("/todas")
    public ResponseEntity<List<PersonaEntity>> listarPersonas() {
        return ResponseEntity.ok(personaService.obtenerTodasLasPersonas());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPersona(@PathVariable int id) {
        return ResponseEntity.ok(personaService.eliminarPersona(id));
    }
}
