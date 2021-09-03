package br.com.minhavacina.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
    private String email;
    private String senha;
    @ManyToMany
    @JoinTable(name = "usuarios_vacinas", joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "vacina_id"))
    private List<Vacina> vacinas;
}
