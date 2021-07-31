package br.com.minhavacina.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity @Table(name = "municipios")
public class Municipio {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
}
