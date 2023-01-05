
package com.escom.tt.controller;

import com.escom.tt.domain.Patada;
import com.escom.tt.domain.Rutina;
import com.escom.tt.domain.Usuario;
import com.escom.tt.repository.PatadaRepository;
import com.escom.tt.repository.RutinaRepository;
import com.escom.tt.repository.UsuarioRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/rutina")
public class RutinaController {
    
    @Autowired
    private SimpMessagingTemplate template;
    
    @Autowired
    private RutinaRepository rutinaRepository;
    
    @Autowired
    private UsuarioRepository usuarioReposirory;
    
    @Autowired
    private PatadaRepository patadaRepository;

    @PostMapping(value = "/nueva")
    public ResponseEntity<?> createRutina(@RequestBody Rutina rutina){
        try {
            Usuario usuario = rutina.getUsuario();
            Optional<Usuario> optionalUsuario = usuarioReposirory.findById(usuario.getId());
            if(optionalUsuario.isEmpty()){
                return new ResponseEntity<>("No se encontro el usuario",HttpStatus.BAD_REQUEST);
            }
            usuario = optionalUsuario.get();
            rutina.setUsuario(usuario);
            rutina.setFecha(LocalDate.now());
             rutina = rutinaRepository.save(rutina);
             this.template.convertAndSend("/call/message/"+rutina.getUsuario().getId(),rutina);
             return new ResponseEntity<>(rutina,HttpStatus.OK);
             
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping(value = "/add-patadas/{rutina}")
    public ResponseEntity<?> addPatadas( @RequestBody List<Patada> patadas, @PathVariable("rutina") Long idRutina) {
        try {
            Optional<Rutina> rutinaOptional = rutinaRepository.findById(idRutina);
            if(rutinaOptional.isEmpty()){
                return new ResponseEntity<>("No se encontro la rutina",HttpStatus.OK);
            }
            patadas.forEach(p -> {
                patadaRepository.save(p);
            });
            Rutina rutina = rutinaOptional.get();
            this.template.convertAndSend("/call/grafica/"+idRutina,rutina);
            return new ResponseEntity<>("Patadas registradas correctamente",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        } 
    }

}