
package com.escom.tt.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements Serializable{
    
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    
    @Column(unique = true)
    String usuario;
    
    @Column
    String contrasena;
    
    @OneToMany(mappedBy = "usuario")
    List<Patada> patadas;
    
}
