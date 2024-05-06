package com.daniel.appsipgaa.models;

import java.util.UUID;

public class DetalleVenta extends BaseModel{

    public String id_venta;
    public String productoId;
    public int unidades;
    public int valorUnitario;
    public int valorTotal;
    public String nombre;

    public DetalleVenta(String IdVenta){
        this.id_venta = IdVenta;
        this.identificador = UUID.randomUUID().toString();
        valorTotal = 0;
    }

    public String getProductoId() {
        return productoId;
    }

    public void setProductoId(String productoId) {
        this.productoId = productoId;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidad() {
        this.unidades++;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public int getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(int valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public int getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(int valorTotal) {
        this.valorTotal += valorTotal;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }
}
