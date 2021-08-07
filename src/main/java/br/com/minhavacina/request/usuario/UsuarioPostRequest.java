package br.com.minhavacina.request.usuario;

import br.com.minhavacina.domain.Municipio;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class UsuarioPostRequest {
    @NotEmpty(message = "Nome não pode ser vazio")
    @Size(max = 50, message = "Nome do usuário não pode passar de {max} caracteres")
    private String nome;

    @NotNull(message = "Data de nascimento do usuário não pode ser nula")
    private Date dataNascimento;

    @NotNull(message = "Município não pode ser nulo")
    private Municipio municipio;

    @NotEmpty(message = "Senha do usuário não pode ser vazia")
    @Size(max = 50, message = "Senha do usuário não pode passar de {max} caracteres")
    private String senha;
}
