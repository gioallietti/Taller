package com.example.Taller.Controller;

import com.example.Taller.Entity.IngresoEntity;
import com.example.Taller.Service.IngresoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
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

    @GetMapping("/ordenPrioridad")
    public ResponseEntity<List<IngresoEntity>> listarPorPrioridad(){
        return ResponseEntity.ok(ingresoService.listarPorPrioridad());
    }

    @GetMapping("/rangoFecha")
    public ResponseEntity<?> buscarPorRangoFecha(@RequestParam String fechaInicio, @RequestParam String fechaFin){
        LocalDate fechaDesde = LocalDate.parse(fechaInicio);
        LocalDate fechaHasta = LocalDate.parse(fechaFin);

        if (fechaDesde.isAfter(fechaHasta)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La fecha de inicio no puede ser despues a la fecha de fin");
        }
        return ResponseEntity.status(HttpStatus.OK).body(ingresoService.ingresosPorFechas(fechaDesde, fechaHasta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IngresoEntity> actualizarIngreso(@PathVariable int id, @RequestBody IngresoEntity ingreso) {
        return ResponseEntity.ok(ingresoService.actualizarIngreso(id, ingreso));
    }
}
