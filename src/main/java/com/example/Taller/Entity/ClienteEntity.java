package com.example.Taller.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class ClienteEntity extends PersonaEntity{

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false)
    private String ciudad;

    @ManyToOne(optional = false)
    private PaisEntity pais;

    @Column(nullable = true)
    private String telefono2;

    @Column(nullable = false)
    private Boolean activo = true;

    public String getTelefono2() {
        return telefono2;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public PaisEntity getPais() {
        return pais;
    }

    public void setPais(PaisEntity pais) {
        this.pais = pais;
    }
}