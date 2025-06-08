package com.example.Taller.Controller;

import com.example.Taller.Entity.TipoEquipoEntity;
import com.example.Taller.Service.TipoEquipoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(
        origins = {"http://localhost:3000"}
)
@RequestMapping({"/TipoEquipos"})
public class TipoEquipoController {
    @Autowired
    private TipoEquipoService tipoEquipoService;

    @PostMapping({"/crea"})
    public ResponseEntity<TipoEquipoEntity> agregarTipoEquipo(@RequestBody TipoEquipoEntity tipoEquipo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.tipoEquipoService.guardarTipoEquipo(tipoEquipo));
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<TipoEquipoEntity> obtenerTipoEquipo(@PathVariable int id) {
        return ResponseEntity.ok(this.tipoEquipoService.obtenerTipoEquipoPorId(id));
    }

    @GetMapping({"/todos"})
    public ResponseEntity<List<TipoEquipoEntity>> listarTipoEquipos() {
        return ResponseEntity.ok(this.tipoEquipoService.obtenerTodosLosTipoEquipos());
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<String> eliminarTipoEquipo(@PathVariable int id) {
        this.tipoEquipoService.eliminarTipoEquipo(id);
        return ResponseEntity.ok("Tipo de equipo eliminado con éxito.");
    }
}
