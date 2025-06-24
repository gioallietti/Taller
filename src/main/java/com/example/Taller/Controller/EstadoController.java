package com.example.Taller.Controller;

import com.example.Taller.Entity.EstadoEntity;
import com.example.Taller.Entity.MarcaEntity;
import com.example.Taller.Service.EstadoService;
import com.example.Taller.Service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(
        origins = {"http://localhost:3000"}
)
@RequestMapping({"/estados"})
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @PostMapping({"/crea"})
    public ResponseEntity<EstadoEntity> guardarEstado(@RequestBody EstadoEntity estado) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.estadoService.guardarEstado(estado));
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<EstadoEntity> obtenerEstado(@PathVariable int id) {
        return ResponseEntity.ok(this.estadoService.obtenerEstadoPorId(id));
    }

    @GetMapping({"/todos"})
    public ResponseEntity<List<EstadoEntity>> listarEstados() {
        return ResponseEntity.ok(this.estadoService.obtenerTodosLosEstados());
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<String> eliminarEstado(@PathVariable int id) {
        this.estadoService.eliminarEstado(id);
        return ResponseEntity.ok("Estado eliminado con éxito.");
    }
}
