package br.com.minhavacina.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenLoginResponse {
    private String tipo;
    private String token;
}
