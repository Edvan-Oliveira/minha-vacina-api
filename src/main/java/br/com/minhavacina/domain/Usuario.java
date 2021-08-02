package br.com.minhavacina.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity @Data
@Table(name = "usuarios")
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    @ManyToOne
    private Municipio municipio;
    private String senha;
}
