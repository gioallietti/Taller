package com.example.Taller.Controller;

import com.example.Taller.Entity.PaisEntity;
import com.example.Taller.Service.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(
        origins = {"*"}
)
@RequestMapping({"/paises"})
public class PaisController {

    @Autowired
    private PaisService paisService;

    @PostMapping({"/crea"})
    public ResponseEntity<PaisEntity> agregarPais(@RequestBody PaisEntity pais) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.paisService.guardarPais(pais));
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<PaisEntity> obtenerPais(@PathVariable int id) {
        return ResponseEntity.ok(this.paisService.obtenerPaisPorId(id));
    }

    @GetMapping({"/todos"})
    public ResponseEntity<List<PaisEntity>> listarPaises() {
        return ResponseEntity.ok(this.paisService.obtenerTodasLosPaises());
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<String> eliminarPais(@PathVariable int id) {
        this.paisService.eliminarPais(id);
        return ResponseEntity.ok("Pais eliminado con éxito.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaisEntity> actualizarPais(@PathVariable int id, @RequestBody PaisEntity pais) {
        return ResponseEntity.ok(paisService.actualizarPais(id, pais));
    }
}
