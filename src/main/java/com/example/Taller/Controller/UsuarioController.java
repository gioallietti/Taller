package com.example.Taller.Controller;

import com.example.Taller.Entity.UsuarioEntity;
import com.example.Taller.Service.UsuarioService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/crea")
    public ResponseEntity<UsuarioEntity> agregarUsuario(@RequestBody UsuarioEntity usuario) throws BadRequestException {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.guardarUsuario(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioEntity> upDateUsuario(@PathVariable Integer id, @RequestBody UsuarioEntity usuario) throws BadRequestException {
        try {
            UsuarioEntity usuarioExiste = usuarioService.obtenerUsuarioPorId(id.toString());
            if (usuarioExiste != null) {
                usuario.setId(id);
                return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.actualizarUsuario(usuario));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{email}")
    public boolean obtenerUsuario(@PathVariable String email) {
        return usuarioService.obtenerUsuarioPorEmail(email);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<UsuarioEntity>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.obtenerTodosLosUsuarios());
    }

    @GetMapping("/tecnicos/{id}")
    public ResponseEntity<List<UsuarioEntity>> listarTecnicos(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.obtenerTodosLosTecnicos(id));
    }

    @DeleteMapping("/{id}")
    public boolean eliminarUsuario(@PathVariable String id) {
        return usuarioService.eliminarUsuario(id);
    }

    @PostMapping ("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioEntity usuario){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.login(usuario));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encontró usuario");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al  conectar con la base de datos");
        }
    }
}
