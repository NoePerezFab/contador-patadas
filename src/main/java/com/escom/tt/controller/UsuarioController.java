
package com.escom.tt.controller;

import com.escom.tt.domain.Patada;
import com.escom.tt.domain.Usuario;
import com.escom.tt.repository.PatadaRepository;
import com.escom.tt.repository.UsuarioRepository;
import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/usuario", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class UsuarioController {
    
    @Autowired
    UsuarioRepository usuarioRepository;
    
    @Autowired
    PatadaRepository patadaRepository;
    
    @PostMapping(value = "/add-usuario")
    public ResponseEntity<?> addUsuario(@RequestBody Usuario usuario){
        
        try {
            usuario.setContrasena(Hashing.sha512().hashString(usuario.getContrasena(), StandardCharsets.UTF_8).toString());
            usuarioRepository.save(usuario);
            return new ResponseEntity<>("Usuario registrado correctamente",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
    
    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario){
        
        try {
            Optional<Usuario> opUsuario = usuarioRepository.findByUsuario(usuario.getUsuario());
            if(opUsuario.isEmpty() || opUsuario.get().getContrasena().compareTo(Hashing.sha512().hashString(usuario.getContrasena(), StandardCharsets.UTF_8).toString()) != 0){
                return new ResponseEntity<>("Usuario o contraseña incorrectos",HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(opUsuario.get(),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
    
    
    @PutMapping(value = "/add-patada-usuario")
    public ResponseEntity<?> addPatadaUsuario(@RequestBody Patada patada){
        
        try {
            patadaRepository.save(patada);
            return new ResponseEntity<>("Patada registrada correctamente",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }  
    }

    @PutMapping(value = "/add-patadas/{patadas}")
    public ResponseEntity<?> addPatadas( @RequestBody List<Patada> patadas) {
        try {
            patadas.forEach(p -> {
                patadaRepository.save(p);
            });
            return new ResponseEntity<>("Patadas registrada correctamente",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        } 
    }

    
}
