package br.com.minhavacina.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ConteudoNaoEncontrado extends RuntimeException {
    public ConteudoNaoEncontrado(String mensagem) {
        super(mensagem);
    }
}
