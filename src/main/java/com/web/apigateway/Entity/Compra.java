package com.web.apigateway.Entity;

import java.util.Set;

public class Compra {

    private int id;

    private Cliente cliente;

    private Set<ArtCompra> articuloCompra;
    private String fechaCompra;


    public Compra() {
    }

    public Compra(Cliente cliente, Set<ArtCompra> articuloCompra, String fechaCompra) {
        this.cliente = cliente;
        this.articuloCompra = articuloCompra;
        this.fechaCompra = fechaCompra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Set<ArtCompra> getArticuloCompra() {
        return articuloCompra;
    }

    public void setArticuloCompra(Set<ArtCompra> articuloCompra) {
        this.articuloCompra = articuloCompra;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
}
