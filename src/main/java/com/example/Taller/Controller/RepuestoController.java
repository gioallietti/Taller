package com.example.Taller.Controller;

import com.example.Taller.Entity.RepuestoEntity;
import com.example.Taller.Service.RepuestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/repuestos")
public class RepuestoController {
    @Autowired
    private RepuestoService repuestoService;

    @PostMapping("/crea")
    public ResponseEntity<RepuestoEntity> guardarRepuesto(@RequestBody RepuestoEntity repuesto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repuestoService.guardarRepuesto(repuesto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepuestoEntity> obtenerRepuesto(@PathVariable int id) {
        return ResponseEntity.ok(repuestoService.obtenerRepuestoPorId(id));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<RepuestoEntity>> listarRepuestos() {
        return ResponseEntity.ok(repuestoService.obtenerTodosLosRepuestos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarRepuesto(@PathVariable int id) {
        return ResponseEntity.ok(repuestoService.eliminarRepuesto(id));
    }

}
