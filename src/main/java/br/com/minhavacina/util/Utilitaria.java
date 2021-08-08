package br.com.minhavacina.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Utilitaria {
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
}
