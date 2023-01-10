
package com.escom.tt.repository;

import com.escom.tt.domain.Rutina;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RutinaRepository extends JpaRepository<Rutina, Long>{
    public List<Rutina> findAllByUsuarioId(Long idUsuario);
}
