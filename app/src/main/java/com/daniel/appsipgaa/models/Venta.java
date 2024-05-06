package com.daniel.appsipgaa.models;

import java.time.Instant;
import java.util.UUID;

public class Venta extends BaseModel{

    private String email_cliente;
    private int total;
    private Instant fechaVenta;

    public Venta(String id_venta, String email_cliente, int total, Instant fechaVenta) {
        this.email_cliente = email_cliente;
        this.total = total;
        this.fechaVenta = fechaVenta;

        this.identificador = id_venta;
    }

    public String getIdCliente() {
        return email_cliente;
    }

    public void setIdCliente(String email_cliente) {
        this.email_cliente = email_cliente;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Instant getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Instant fechaVenta) {
        this.fechaVenta = fechaVenta;
    }
}
