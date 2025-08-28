package com.example.Taller.Controller;

import com.example.Taller.Entity.IngresoEntity;
import com.example.Taller.Entity.IngresoRepuestoEntity;
import com.example.Taller.Entity.RepuestoEntity;
import com.example.Taller.Service.IngresoRepuestoService;
import com.example.Taller.Service.IngresoService;
import com.example.Taller.Service.RepuestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/ingresos")
public class IngresoController {
    @Autowired
    private IngresoService ingresoService;

    @Autowired
    private RepuestoService repuestoService;

    @Autowired
    private IngresoRepuestoService ingresoRepuestoService;

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
    public ResponseEntity<List<IngresoEntity>> listarPorPrioridad() {
        return ResponseEntity.ok(ingresoService.listarPorPrioridad());
    }

    @GetMapping("/rangoFecha")
    public ResponseEntity<?> buscarPorRangoFecha(@RequestParam String fechaInicio, @RequestParam String fechaFin) {
        LocalDate fechaDesde = LocalDate.parse(fechaInicio);
        LocalDate fechaHasta = LocalDate.parse(fechaFin);

        if (fechaDesde.isAfter(fechaHasta)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La fecha de inicio no puede ser despues a la fecha de fin");
        }
        return ResponseEntity.status(HttpStatus.OK).body(ingresoService.ingresosPorFechas(fechaDesde, fechaHasta));
    }

    @GetMapping("/registradoPor/{id}")
    public ResponseEntity<?> findAllByRegistradoPor_Id(@PathVariable Integer id) {
        if (id != null) {
            return ResponseEntity.status(HttpStatus.OK).body(ingresoService.findAllByRegistradoPor_Id(id));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No hay ingresos por este usuarios");
    }

    @GetMapping("/reparados/{estadoId}/{tipoUsuarioId}")
    public ResponseEntity<?> ingresoPorEstado_IdTipoUsuario(@PathVariable Integer estadoId,@PathVariable Integer tipoUsuarioId) {
        if (estadoId != null && tipoUsuarioId != null) {
            return ResponseEntity.status(HttpStatus.OK).body(ingresoService.ingresoPorEstado_IdTipoUsuario(estadoId, tipoUsuarioId));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No hay ingresos");
    }

    @GetMapping("/porTecnicos/{id}")
    public ResponseEntity<?> ingresoTecnicos(@PathVariable Integer id) {
        if ( id != null) {
            return ResponseEntity.status(HttpStatus.OK).body(ingresoService.findAllByTecnicos( id));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No hay ingresos");
    }

    @PutMapping("/{id}")
    public ResponseEntity<IngresoEntity> actualizarIngreso(@PathVariable int id, @RequestBody IngresoEntity ingreso) {

        List<IngresoRepuestoEntity> ingresoRepuestoEntityList = ingresoRepuestoService.obtenerIngresoRepuestoPorIngresoId(ingreso.getId());

        for (IngresoRepuestoEntity ingresoRepuesto : ingresoRepuestoEntityList) {
            if (ingresoRepuesto == null) continue;
            RepuestoEntity repuesto = repuestoService.obtenerRepuestoPorId(ingresoRepuesto.getRepuesto().getId());

            int cantidad = ingresoRepuesto.getCantidad();
            if(repuesto != null) {

                repuestoService.updateCantidadCopias(repuesto.getId(), -cantidad);
            }
        }

        List<IngresoRepuestoEntity> ingresoRepuestoEntityList2 = new ArrayList<>();
        for (IngresoRepuestoEntity ingresoRepuesto : ingreso.getIngresoRepuestos()) {
            RepuestoEntity repuesto = repuestoService.obtenerRepuestoPorId(ingresoRepuesto.getId());

            int cantidad = ingresoRepuesto.getCantidad();
            if(repuesto != null) {
                IngresoRepuestoEntity ingresoRepuestoEntity = new IngresoRepuestoEntity();
                ingresoRepuestoEntity.setIngreso(ingreso);
                ingresoRepuestoEntity.setRepuesto(repuesto);
                ingresoRepuestoEntity.setCantidad(cantidad);

                ingresoRepuestoEntityList2.add(ingresoRepuestoEntity);

                repuestoService.updateCantidadCopias(ingresoRepuesto.getId(), cantidad);
            }
        }
        ingreso.setIngresoRepuestos(ingresoRepuestoEntityList2);
        return ResponseEntity.ok(ingresoService.actualizarIngreso(id, ingreso));
    }

    @PutMapping("/guardarMensaje/{id}")
    public ResponseEntity<IngresoEntity> guardarMensaje(@PathVariable int id, @RequestBody IngresoEntity ingreso) {
        return ResponseEntity.ok(ingresoService.guardarMensaje(id, ingreso));
    }

    @PutMapping("/estado/{id}")
    public ResponseEntity<IngresoEntity> actualizarIngresoEstado(@PathVariable int id, @RequestBody IngresoEntity ingreso) {

        return ResponseEntity.ok(ingresoService.actualizarIngresoEstado(id, ingreso));
    }

    @GetMapping("/finalizadosTresMeses")
    public ResponseEntity<List<IngresoEntity>> listarIngresosFinalizadosAntiguos() {
        List<IngresoEntity> ingresos = ingresoService.IngresosFinalizadosMasTresMeses();
        return ResponseEntity.ok(ingresos);
    }

    @GetMapping("/noFinalizados")
    public ResponseEntity<List<IngresoEntity>> listarNoFinalizados() {
        return ResponseEntity.ok(ingresoService.listarIngresosNoFinalizados());
    }

    @GetMapping("/finalizados")
    public ResponseEntity<List<IngresoEntity>> listarFinalizados() {
        return ResponseEntity.ok(ingresoService.listarIngresosFinalizados());
    }

}
