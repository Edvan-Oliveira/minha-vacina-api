package br.com.minhavacina.request;

import br.com.minhavacina.domain.Municipio;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class UsuarioPutRequest {
    @NotNull(message = "Id não pode ser nulo")
    private Integer id;

    @NotEmpty(message = "Nome não pode ser vazio")
    @Size(max = 50, message = "Nome não pode passar de {max} caracteres")
    private String nome;

    @NotNull(message = "Data de nascimento não pode ser nula")
    private Date dataNascimento;

    @NotNull(message = "Município não pode ser nulo")
    private Municipio municipio;

    @NotEmpty(message = "Email não pode ser vázio")
    @Email(message = "Esse email é inválido")
    private String email;

    @NotEmpty(message = "Senha não pode ser vazia")
    @Size(max = 50, message = "Senha não pode passar de {max} caracteres")
    private String senha;
}
