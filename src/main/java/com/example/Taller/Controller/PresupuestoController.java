package com.example.Taller.Controller;

import com.example.Taller.Entity.PresupuestoEntity;
import com.example.Taller.Service.PresupuestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/presupuestos")
public class PresupuestoController {
    @Autowired
    private PresupuestoService presupuestoService;

    @PostMapping("/crea")
    public ResponseEntity<PresupuestoEntity> agregarPresupuesto(@RequestBody PresupuestoEntity presupuesto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(presupuestoService.guardarPresupuesto(presupuesto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PresupuestoEntity> obtenerPresupuesto(@PathVariable int id) {
        return ResponseEntity.ok(presupuestoService.obtenerPresupuestoPorId(id));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<PresupuestoEntity>> listarPresupuestos() {
        return ResponseEntity.ok(presupuestoService.obtenerTodosLosPresupuestos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPresupuesto(@PathVariable int id) {
        return ResponseEntity.ok(presupuestoService.eliminarPresupuesto(id));
    }
}
