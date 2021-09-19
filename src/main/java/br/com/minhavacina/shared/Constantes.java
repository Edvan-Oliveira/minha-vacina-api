package br.com.minhavacina.shared;

public interface Constantes {
    String PADRAO = "/minha-vacina-api";
    String MUNICIPIO = PADRAO + "/municipios";
    String USUARIO = PADRAO + "/usuarios";
    String CAMPANHA = PADRAO + "/campanhas";
    String VACINA = PADRAO + "/vacinas";
    String LOGIN = "/login";
    String LOGIN_ABSOLUTO =  USUARIO + "/login";
    String VALIDA_EMAIL = "/validar-email/{email}";
}
