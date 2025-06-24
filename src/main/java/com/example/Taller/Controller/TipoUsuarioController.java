package com.example.Taller.Controller;

import com.example.Taller.Entity.TipoUsuarioEntity;
import com.example.Taller.Service.TipoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(
        origins = {"http://localhost:3000"}
)
@RequestMapping({"/TipoUsuarios"})
public class TipoUsuarioController {
    @Autowired
    private TipoUsuarioService tipoUsuarioService;

    @PostMapping({"/crea"})
    public ResponseEntity<TipoUsuarioEntity> guardarTipoUsuario(@RequestBody TipoUsuarioEntity tipoUsuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.tipoUsuarioService.guardarTipoUsuario(tipoUsuario));
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<TipoUsuarioEntity> obtenerTipoUsuario(@PathVariable int id) {
        return ResponseEntity.ok(this.tipoUsuarioService.obtenerTipoUsuarioPorId(id));
    }

    @GetMapping({"/todos"})
    public ResponseEntity<List<TipoUsuarioEntity>> listarTipoUsuarios() {
        return ResponseEntity.ok(this.tipoUsuarioService.obtenerTodosLosTipoUsuarios());
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<String> eliminarTipoUsuario(@PathVariable int id) {
        this.tipoUsuarioService.eliminarTipoUsuario(id);
        return ResponseEntity.ok("Tipo de usuario eliminado con éxito.");
    }
}
