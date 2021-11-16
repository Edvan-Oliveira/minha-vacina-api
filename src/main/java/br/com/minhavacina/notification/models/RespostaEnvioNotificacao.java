package br.com.minhavacina.notification.models;

import lombok.Data;

import java.util.List;

@Data
public class RespostaEnvioNotificacao {
    private List<DadosRespostaNotificacao> data;
}
