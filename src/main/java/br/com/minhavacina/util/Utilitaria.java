package br.com.minhavacina.util;

import br.com.minhavacina.domain.Usuario;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Utilitaria {

    private static PasswordEncoder encoderSenha = new BCryptPasswordEncoder();

    public static Date converterDataTextoParaDataUtil(String dataTexto) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dataTexto);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String converterDataUtilParaDataTexto(Date dataUtil) {
        return new SimpleDateFormat("yyyy-MM-dd").format(dataUtil);
    }

    public static Boolean objetoEstarNuloOuVazio(Object objeto) {
        if (objeto == null) return true;
        if (objeto.equals("")) return true;
        return false;
    }

    public static Boolean objetoNaoEstarNuloNemVazio(Object objeto) {
        if (objeto != null) {
            if (!objeto.equals("")) return true;
        }
        return false;
    }

    public static void criptografarSenha(Usuario usuario) {
         usuario.setSenha(encoderSenha.encode(usuario.getSenha()));
    }
}
