
package com.escom.tt.controller;

import com.escom.tt.domain.Patada;
import com.escom.tt.domain.Usuario;
import com.escom.tt.repository.PatadaRepository;
import com.escom.tt.repository.UsuarioRepository;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/usuario", produces = MediaType.APPLICATION_JSON_VALUE)
public class Controller {
    
    @Autowired
    UsuarioRepository usuarioRepository;
    
    @Autowired
    PatadaRepository patadaRepository;
    
    @PostMapping(value = "/add-usuario")
    public ResponseEntity<?> addUsuario(@RequestBody Usuario usuario){
        
        try {
            usuarioRepository.save(usuario);
            return new ResponseEntity<>("Usuario registrado correctamente",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
    
    @PutMapping(value = "/add-patada-usuario")
    public ResponseEntity<?> addPatadaUsuario(@RequestBody Patada patada){
        
        try {
            Usuario u = usuarioRepository.findById(patada.getUsuario().getId()).get();
            patada.setTimeStamp(new Timestamp(System.currentTimeMillis()));
            patadaRepository.save(patada);
            return new ResponseEntity<>("Patada registrada correctamente",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }  
    }

    @PutMapping(value = "/add-patadas/{patadas}")
    public ResponseEntity<?> addPatadas(@PathVariable("patadas") Long patadas, @RequestBody Usuario usuario) {
        try {
            Usuario u = usuarioRepository.findById(usuario.getId()).get();
            for(Long i = 0L; i < patadas; i++){
                Patada patada = new Patada();
                patada.setUsuario(u);
                patadaRepository.save(patada);
            }
            return new ResponseEntity<>("Patadas registrada correctamente",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        } 
    }

    
}
