package br.com.minhavacina.request.campanha;

import br.com.minhavacina.domain.Municipio;
import br.com.minhavacina.domain.Vacina;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class CampanhaPostRequest {
    @NotEmpty(message = "Nome da campanha não pode ser vazio")
    @Size(max = 25, message = "Nome da campanha não pode passar de {max} caracteres")
    private String nome;

    @NotEmpty(message = "Descrição da campanha não pode ser vazio")
    @Size(max = 4000, message = "Descrição da campanha não pode passar de {max) caracteres")
    private String descricao;

    @NotNull(message = "Vacina da campanha não pode ser nula")
    private Vacina vacina;

    @NotNull(message = "Município da campanha não pode ser nulo")
    private Municipio municipio;

    private boolean ativa;
    private Date dataInicio;
    private Date dataFim;

    @Size(min = 0, message = "Idade mínima da campanha não pode ser menor que {min}")
    private Integer idadeMinima;

    @Size(min = 0, message = "Idade máxima da campanha não pode ser menor que {min}")
    private Integer idadeMaxima;
}
