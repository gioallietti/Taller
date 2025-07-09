package com.example.Taller.Controller;

import com.example.Taller.Entity.UsuarioEntity;
import com.example.Taller.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/crea")
    public ResponseEntity<UsuarioEntity> agregarUsuario(@RequestBody UsuarioEntity usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.guardarUsuario(usuario));
    }

    @GetMapping("/{email}")
    public boolean obtenerUsuario(@PathVariable String email) {
        return usuarioService.obtenerUsuarioPorEmail(email);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<UsuarioEntity>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.obtenerTodosLosUsuarios());
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable String email) {
        return ResponseEntity.ok(usuarioService.eliminarUsuario(email));
    }

    @PostMapping ("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioEntity usuario){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.login(usuario));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encontró usuario");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
