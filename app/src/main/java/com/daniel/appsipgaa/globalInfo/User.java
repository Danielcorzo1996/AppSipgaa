package com.daniel.appsipgaa.globalInfo;

public class User{

    private static User instance;

    private String email;

    private User() {
        // Constructor privado para evitar la creación de instancias desde fuera de la clase
    }


    public static User getInstance() {
        // Si la instancia es nula, se crea una nueva instancia
        if (instance == null) {
            instance = new User();
        }
        // Devuelve la instancia existente o recién creada
        return instance;
    }

    public void setEmail(String email){ this.email = email; }

    public String getEmail(){ return email; }
}
