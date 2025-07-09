package com.example.Taller.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;
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

    @ManyToOne(optional = false)
    private UsuarioEntity registradoPor;

    @ManyToOne(optional = false)
    private UsuarioEntity reparadoPor;

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
    private LocalDate fechaIngreso;

    private LocalDate fechaFinalizacion;

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

    public UsuarioEntity getRegistradoPor() { return registradoPor; }

    public void setRegistradoPor(UsuarioEntity registradoPor) { this.registradoPor = registradoPor; }

    public UsuarioEntity getReparadoPor() { return reparadoPor; }

    public void setReparadoPor(UsuarioEntity reparadoPor) { this.reparadoPor = reparadoPor; }

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

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public LocalDate getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(LocalDate fechaFinalizacion) {
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
