package com.daniel.appsipgaa.models;

import java.io.Serializable;
import java.util.UUID;

public class Producto implements Serializable {

    private String nombre;
    private  String descripcion;
    private int stock;
    private int valor;
    private String id_producto;
    private String urlImage;


    public Producto( String descripcion, String id_producto, String nombre, int stock, int valor, String urlImage) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.stock = stock;
        this.valor = valor;
        this.urlImage = urlImage;

        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getId_producto() {
        return id_producto;
    }

    public void setId_producto(String id_producto) {
        this.id_producto = id_producto;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
