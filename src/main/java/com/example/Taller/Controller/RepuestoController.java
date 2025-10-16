package com.example.Taller.Controller;

import com.example.Taller.Entity.RepuestoEntity;
import com.example.Taller.Service.RepuestoService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/repuestos")
public class RepuestoController {
    @Autowired
    private RepuestoService repuestoService;

    @PostMapping("/crea")
    public ResponseEntity<RepuestoEntity> guardarRepuesto(@RequestBody RepuestoEntity repuesto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repuestoService.guardarRepuesto(repuesto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RepuestoEntity> upDateRepuesto(@PathVariable Integer id, @RequestBody RepuestoEntity repuesto) throws BadRequestException {
        try {
            RepuestoEntity repuestoExiste = repuestoService.obtenerRepuestoPorId(id);
            if (repuestoExiste != null) {
                repuesto.setId(id);
                return ResponseEntity.status(HttpStatus.CREATED).body(repuestoService.actualizarRepuesto(repuesto));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
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
