
package com.escom.tt.repository;

import com.escom.tt.domain.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    public Optional<Usuario> findByUsuario(String usuario);
}
