package com.daniel.appsipgaa;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Helpers implements  IHelpers{

    private static String EMAIL_REGEX =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /**
     * Valida sí todos los campos cumplen con las condiciones necesarias
     * para poder crea run usuario nuevo
     * @param user
     * @param password
     * @param email
     * @return true si todo esta ok, false si esta mal
     */
    @Override
    public String checkFieldsOk(String user, String password, String email){
        if(user != null){
            return checkWithAllFiled(user, password, email);
        }
        return checkWithOutUser(password, email);
    }

    private String checkWithOutUser( String password, String email){

        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);

        if(email.isEmpty() ||  !matcher.matches()){
            return "Ingresa un Correo valido";
        }

        if(password.isEmpty()){
            return "Ingresa una Clave";
        }

        if(password.length() < 6){
            return "Clave minimo 6 caracteres!";
        }

        return "";
    }

    private String checkWithAllFiled(String user, String password, String email){
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
