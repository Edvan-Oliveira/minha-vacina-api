package br.com.minhavacina.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Local {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descricao;
    private String cep;
    private String bairro;
    private String rua;
    private String numero;
    @ManyToOne
    private Municipio municipio;
}
