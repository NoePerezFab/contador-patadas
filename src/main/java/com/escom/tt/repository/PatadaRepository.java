
package com.escom.tt.repository;

import com.escom.tt.domain.Patada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatadaRepository extends JpaRepository<Patada, Long>{
    
}
