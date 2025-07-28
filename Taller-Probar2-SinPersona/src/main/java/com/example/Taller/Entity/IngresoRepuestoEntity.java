package com.example.Taller.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ingreso_repuesto")
public class IngresoRepuestoEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ingreso_id", nullable = false)
    private IngresoEntity ingreso;

    @ManyToOne
    @JoinColumn(name = "repuesto_id", nullable = false)
    private RepuestoEntity repuesto;

    @Column(nullable = false)
    private Integer cantidad;

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

    public RepuestoEntity getRepuesto() {
        return repuesto;
    }

    public void setRepuesto(RepuestoEntity repuesto) {
        this.repuesto = repuesto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
