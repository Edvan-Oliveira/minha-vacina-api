package br.com.minhavacina.request.usuario;

import br.com.minhavacina.domain.Municipio;
import br.com.minhavacina.domain.Permissao;
import br.com.minhavacina.domain.Vacina;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UsuarioGetRequest {
    private Integer id;
    private String nome;
    private String email;
    private Date dataNascimento;
    private Municipio municipio;
    private List<Vacina> vacinas;
    private List<Permissao> permissoes;
}
