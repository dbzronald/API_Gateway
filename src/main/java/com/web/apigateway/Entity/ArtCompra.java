package com.web.apigateway.Entity;

public class ArtCompra {

    private int id;

    private Articulo articulo;

    private Boolean entregado;

    private int cantidad;

    private String fecha;

    public ArtCompra() {
        entregado = false;
        cantidad = 0;
    }

    public ArtCompra(Articulo articulo, Boolean entregado, int cantidad, String fecha) {
        this.articulo = articulo;
        this.entregado = entregado;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Boolean getEntregado() {
        return entregado;
    }

    public void setEntregado(Boolean entregado) {
        this.entregado = entregado;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}