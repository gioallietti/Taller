package com.example.Taller.DTO;

public class IngreosPorMesAnioDTO {
    private String mesAnio;
    private Integer cantidad;
    private Integer cantidadReparados;

    public String getMesAnio() {
        return mesAnio;
    }

    public void setMesAnio(String mesAnio) {
        this.mesAnio = mesAnio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getCantidadReparados() {
        return cantidadReparados;
    }

    public void setCantidadReparados(Integer cantidadReparados) {
        this.cantidadReparados = cantidadReparados;
    }
}
