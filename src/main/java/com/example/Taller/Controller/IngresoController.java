package com.example.Taller.Controller;

import com.example.Taller.Entity.IngresoEntity;
import com.example.Taller.Service.IngresoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/ingresos")
public class IngresoController {
    @Autowired
    private IngresoService ingresoService;

    @PostMapping("/crea")
    public ResponseEntity<IngresoEntity> agregarIngreso(@RequestBody IngresoEntity ingreso) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ingresoService.guardarIngreso(ingreso));
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngresoEntity> obtenerIngreso(@PathVariable int id) {
        return ResponseEntity.ok(ingresoService.obtenerIngresoPorId(id));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<IngresoEntity>> listarIngresos() {
        return ResponseEntity.ok(ingresoService.obtenerTodosLosIngresos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarIngreso(@PathVariable int id) {
        return ResponseEntity.ok(ingresoService.eliminarIngreso(id));
    }
}
