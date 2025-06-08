package com.example.Taller.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "presupuestos")
public class PresupuestoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(optional = false)
    private IngresoEntity ingreso;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private Double manoDeObra;

    @Column(nullable = false)
    private Double costoRepuesto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public IngresoEntity getIngreso() {
        return ingreso;
    }

    public void setIngreso(IngresoEntity ingreso) {
        this.ingreso = ingreso;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Double getManoDeObra() {
        return manoDeObra;
    }

    public void setManoDeObra(Double manoDeObra) {
        this.manoDeObra = manoDeObra;
    }

    public Double getCostoRepuesto() {
        return costoRepuesto;
    }

    public void setCostoRepuesto(Double costoRepuesto) {
        this.costoRepuesto = costoRepuesto;
    }
}
