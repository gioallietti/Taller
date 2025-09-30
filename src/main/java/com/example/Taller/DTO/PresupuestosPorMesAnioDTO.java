package com.example.Taller.DTO;

public class PresupuestosPorMesAnioDTO {
    private String mesAnio;
    private Integer sumaTotal;
    private Integer sumaManoDeObra;
    private Integer sumaCostoRepuestos;

    public String getMesAnio() {
        return mesAnio;
    }

    public void setMesAnio(String mesAnio) {
        this.mesAnio = mesAnio;
    }

    public Integer getSumaTotal() {
        return sumaTotal;
    }

    public void setSumaTotal(Integer sumaTotal) {
        this.sumaTotal = sumaTotal;
    }

    public Integer getSumaManoDeObra() {
        return sumaManoDeObra;
    }

    public void setSumaManoDeObra(Integer sumaManoDeObra) {
        this.sumaManoDeObra = sumaManoDeObra;
    }

    public Integer getSumaCostoRepuestos() {
        return sumaCostoRepuestos;
    }

    public void setSumaCostoRepuestos(Integer sumaCostoRepuestos) {
        this.sumaCostoRepuestos = sumaCostoRepuestos;
    }
}
