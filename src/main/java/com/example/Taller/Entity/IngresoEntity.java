package com.example.Taller.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ingresos")

public class IngresoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    private ClienteEntity cliente;

    @ManyToMany
    @JoinTable(name = "ingresoEquipo")
    private List<EquipoEntity> equipos;

    @Column(nullable = false)
    private String modelo;

    @Column(name = "numeroSerie", nullable = false, unique = true)
    private String numeroSerie;

    @Column(nullable = false)
    private String problema;

    @ManyToOne(optional = false)
    private PrioridadEntity prioridad;

    @Column(nullable = false)
    private LocalDateTime fechaIngreso;

    private LocalDateTime fechaFinalizacion;

    @ManyToOne(optional = false)
    private EstadoEntity estado;

    private String detalle;

    @Column(nullable = false)
    private boolean presupuestado;

    @ManyToMany
    @JoinTable(name = "ingresoRepuesto")
    private List<RepuestoEntity> repuestos;

    public List<RepuestoEntity> getRepuestos() {
        return repuestos;
    }

    public void setRepuestos(List<RepuestoEntity> repuestos) {
        this.repuestos = repuestos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public List<EquipoEntity> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<EquipoEntity> equipos) {
        this.equipos = equipos;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    public PrioridadEntity getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(PrioridadEntity prioridad) {
        this.prioridad = prioridad;
    }

    public LocalDateTime getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDateTime fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public LocalDateTime getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(LocalDateTime fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public EstadoEntity getEstado() {
        return estado;
    }

    public void setEstado(EstadoEntity estado) {
        this.estado = estado;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public boolean isPresupuestado() {
        return presupuestado;
    }

    public void setPresupuestado(boolean presupuestado) {
        this.presupuestado = presupuestado;
    }
}
