package com.example.Taller.Controller;

import com.example.Taller.Entity.MarcaEntity;
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
@RequestMapping({"/marcas"})
public class MarcaController {
    @Autowired
    private MarcaService marcaService;

    @PostMapping({"/crea"})
    public ResponseEntity<MarcaEntity> agregarMarca(@RequestBody MarcaEntity marca) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.marcaService.guardarMarca(marca));
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<MarcaEntity> obtenerMarca(@PathVariable int id) {
        return ResponseEntity.ok(this.marcaService.obtenerMarcaPorId(id));
    }

    @GetMapping({"/todas"})
    public ResponseEntity<List<MarcaEntity>> listarMarcas() {
        return ResponseEntity.ok(this.marcaService.obtenerTodasLasMarcas());
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<String> eliminarMarca(@PathVariable int id) {
        this.marcaService.eliminarMarca(id);
        return ResponseEntity.ok("Marca eliminada con éxito.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarcaEntity> actualizarMarca(@PathVariable int id, @RequestBody MarcaEntity marca) {
        return ResponseEntity.ok(marcaService.actualizarMarca(id, marca));
    }
}
