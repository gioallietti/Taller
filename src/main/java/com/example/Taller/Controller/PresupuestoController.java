package com.example.Taller.Controller;

import com.example.Taller.DTO.IngreosPorMesAnioDTO;
import com.example.Taller.DTO.PresupuestosPorMesAnioDTO;
import com.example.Taller.Entity.PresupuestoEntity;
import com.example.Taller.Service.PresupuestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @PutMapping("/{id}")
    public ResponseEntity<PresupuestoEntity> actualizarPresupuesto(@PathVariable int id, @RequestBody PresupuestoEntity presupuesto) {
        return ResponseEntity.ok(presupuestoService.actualizarPresupuesto(id, presupuesto));
    }

    @GetMapping("/ingreso/{id}")
    public ResponseEntity<PresupuestoEntity> obtenerPresupuestoPorIngresoId(@PathVariable int id) {
        return ResponseEntity.ok(presupuestoService.obtenerPresupuestoPorIngresoId(id));
    }


    @GetMapping("/{id}/ganancia")
    public ResponseEntity<Double> obtenerGananciaPorPresupuesto(@PathVariable int id) {
        PresupuestoEntity presupuesto = presupuestoService.obtenerPresupuestoPorId(id);
        if (presupuesto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        double ganancia = presupuestoService.calcularGanancia(presupuesto);
        return ResponseEntity.ok(ganancia);
    }

    @GetMapping("/ganancia/mes/{anio}/{mes}")
    public ResponseEntity<Double> obtenerGananciaPorMes(@PathVariable int anio, @PathVariable int mes) {
        double ganancia = presupuestoService.calcularGananciaMensual(anio, mes);
        return ResponseEntity.ok(ganancia);
    }

    @GetMapping("/ganancia/anio/{anio}")
    public ResponseEntity<Double> obtenerGananciaPorAnio(@PathVariable int anio) {
        double ganancia = presupuestoService.calcularGananciaAnual(anio);
        return ResponseEntity.ok(ganancia);
    }

    @GetMapping("/presupuestosPorAnio")
    public ResponseEntity<List<PresupuestosPorMesAnioDTO> > obtenerPresupuestosPorAnio() {

        return ResponseEntity.ok(presupuestoService.obtenerPresupuestosPorAnio());
    }

    @GetMapping("/presupuestosPorMes")
    public ResponseEntity<List<PresupuestosPorMesAnioDTO> > obtenerPresupuestosPorMes() {

        return ResponseEntity.ok(presupuestoService.obtenerPresupuestosPorMes());
    }

    @GetMapping("/ganancia/rango")
    public ResponseEntity<Double> obtenerGananciaPorFechas(@RequestParam LocalDate fechaInicio, @RequestParam LocalDate fechaFin) {
        double ganancia = presupuestoService.calcularGananciaPorFechas(fechaInicio, fechaFin);
        return ResponseEntity.ok(ganancia);
    }

    @GetMapping("/ingreso/{ingresoId}/costoRealRepuestos")
    public ResponseEntity<Double> obtenerCostoRealRepuestos(@PathVariable Integer ingresoId) {
        double costoReal = presupuestoService.obtenerCostoRealRepuestosPorIngreso(ingresoId);
        return ResponseEntity.ok(costoReal);
    }
}
