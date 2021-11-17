package br.com.minhavacina.request.usuario;

import br.com.minhavacina.domain.Campanha;
import br.com.minhavacina.domain.Municipio;
import br.com.minhavacina.domain.Permissao;
import br.com.minhavacina.domain.Vacina;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UsuarioGetRequest {
    private Integer id;
    private String nome;
    private String email;
    private Date dataNascimento;
    @JsonIgnoreProperties( { "campanhas"})
    private Municipio municipio;
    @JsonIgnoreProperties( { "usuarios", "campanhas"})
    private List<Vacina> vacinas;
    @JsonIgnoreProperties( { "usuarios", "municipio" } )
    private List<Campanha> campanhas;
    private List<Permissao> permissoes;
}
