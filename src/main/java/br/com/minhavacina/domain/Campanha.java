package br.com.minhavacina.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data @Entity
@Table(name = "campanhas")
public class Campanha {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String descricao;
    @JsonIgnoreProperties("campanhas")
    @ManyToOne
    private Vacina vacina;
    @JsonIgnoreProperties("campanhas")
    @ManyToOne
    private Municipio municipio;
    private boolean ativa;
    private Date dataInicio;
    private Date dataFim;
    private Integer idadeMinima;
    private Integer idadeMaxima;
}