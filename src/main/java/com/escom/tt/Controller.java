
package com.escom.tt;

import com.escom.tt.domain.Patada;
import com.escom.tt.domain.Usuario;
import com.escom.tt.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/usuario")
public class Controller {
    
    @Autowired
    UsuarioRepository usuarioRepository;
    
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
    public ResponseEntity<?> addUsuario(@RequestBody Patada patada){
        
        try {
            Usuario u = usuarioRepository.findById(patada.getId()).get();
            u.getPatadas().add(patada);
            usuarioRepository.save(u);
            return new ResponseEntity<>("Patada registrada correctamente",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
    
}
