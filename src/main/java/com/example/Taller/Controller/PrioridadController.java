package com.example.Taller.Controller;

import com.example.Taller.Entity.PrioridadEntity;
import com.example.Taller.Service.PrioridadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/prioridades")
public class PrioridadController {
    @Autowired
    private PrioridadService prioridadService;

    @PostMapping("/crea")
    public ResponseEntity<PrioridadEntity> agregarPrioridad(@RequestBody PrioridadEntity prioridad) {
        return ResponseEntity.status(HttpStatus.CREATED).body(prioridadService.guardarPrioridad(prioridad));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrioridadEntity> obtenerPrioridad(@PathVariable int id) {
        return ResponseEntity.ok(prioridadService.obtenerPrioridadPorId(id));
    }

    @GetMapping("/todas")
    public ResponseEntity<List<PrioridadEntity>> listarPrioridades() {
        return ResponseEntity.ok(prioridadService.obtenerTodasLasPrioridades());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPrioridad(@PathVariable int id) {
        return ResponseEntity.ok(prioridadService.eliminarPrioridad(id));
    }
}
