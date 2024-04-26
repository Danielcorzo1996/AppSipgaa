package com.daniel.appsipgaa;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Helpers {

    /**
     * Valida sí todos los campos cumplen con las condiciones necesarias
     * para poder crea run usuario nuevo
     * @param user
     * @param password
     * @param email
     * @return true si todo esta ok, false si esta mal
     */
    public String checkFieldsOk(String user, String password, String email){
        String EMAIL_REGEX =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                        "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);

        if(user.isEmpty()){
            return "Ingresa un Usuario";
        }

        if(password.isEmpty()){
            return "Ingresa una Clave";
        }

        if(password.length() < 6){
            return "Clave minimo 6 caracteres!";
        }

        if(email.isEmpty() ||  !matcher.matches()){
            return "Ingresa un Correo valido";
        }

        return "";
    }
}
