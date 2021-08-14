package br.com.minhavacina.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data @Entity
@Table(name = "vacinas")
public class Vacina {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String descricao;
    private String caminhoImagem;
    @JsonIgnoreProperties("vacina")
    @OneToMany(mappedBy = "vacina")
    private List<Campanha> campanhas;
}
