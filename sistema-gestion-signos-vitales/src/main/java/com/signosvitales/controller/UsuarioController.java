package com.signosvitales.controller;

import com.signosvitales.model.Usuario;
import com.signosvitales.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // GET http://localhost:8080/api/usuarios
    //8080 es el puerto predeterminado de Tomcat (el servidor web que Spring Boot trae integrado)
    @GetMapping
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    // POST http://localhost:8080/api/usuarios
    @PostMapping
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {
        if (usuarioRepository.existsByUsuario(usuario.getUsuario())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El nombre de usuario ya está registrado.");
        }
        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }
}