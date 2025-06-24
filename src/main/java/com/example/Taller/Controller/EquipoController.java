package com.example.Taller.Controller;

import com.example.Taller.Entity.EquipoEntity;
import com.example.Taller.Entity.TipoEquipoEntity;
import com.example.Taller.Service.EquipoService;
import com.example.Taller.Service.MarcaService;
import com.example.Taller.Service.TipoEquipoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/equipos"})
@CrossOrigin(
        origins = {"http://localhost:3000"}
)
public class EquipoController {
    @Autowired
    private MarcaService marcaService;
    @Autowired
    private TipoEquipoService tipoEquipoService;
    @Autowired
    private EquipoService equipoService;

    @PostMapping({"/crea"})
    public ResponseEntity<EquipoEntity> guardarEquipo(@RequestBody EquipoEntity equipo) {
        return ResponseEntity.ok(equipoService.guardarEquipo(equipo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipoEntity> obtenerEquipoPorId(@PathVariable int id) {
        return ResponseEntity.ok(equipoService.obtenerEquipoPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<EquipoEntity>> obtenerTodosLosEquipos() {
        return ResponseEntity.ok(equipoService.obtenerTodosLosEquipos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEquipo(@PathVariable int id) {
        return ResponseEntity.ok(equipoService.eliminarEquipo(id));
    }

    /*@GetMapping({"/tipoEquipo/{idTipoEquipo}"})
    public ResponseEntity<?> buscarPorTipoEquipo(@PathVariable int idTipoEquipo) {
        TipoEquipoEntity tipoEquipo = (TipoEquipoEntity)this.tipoEquipoService.findById(idTipoEquipo).orElseThrow(() -> new EntityNotFoundException("Tipo de equipo no encontrado"));
        return ResponseEntity.status(HttpStatus.OK).body(this.equipoService.buscarPorTipoEquipo(tipoEquipo));
    }*/
}
